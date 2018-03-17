package parserSQL;

import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

/*
 * Class for cheking exists DataBase, Table, Adding new records in DataBase.
 */
public class CheckConnectToDataBase {
    Storage storage = new Storage();
    private static final Logger log = LoggerFactory.getLogger(CheckConnectToDataBase.class.getName());

    public void check() {
        boolean flagDataBaseExists = connectToPostgres("fullPath");
        if (!flagDataBaseExists) {
            createDataBase(storage.getValue("dbName"));
        }
        if (!checkTableExists(storage.getValue("tableName"))) {
            createTable(storage.getValue("tableName"));
        }
        SQLruParser sqLruParser = new SQLruParser();
        try {
            sqLruParser.setLastTime(lastRecordTime());
        } catch (RuntimeException e) {
            log.warn("I don't find records and I start finding all page per year.  ", e);
        }
        sqLruParser.start();
        List<ItemSQL> list = sqLruParser.getItemSQLList();
        list.forEach(value -> {
            insertToDB(value);
        });
    }

    public boolean connectToPostgres(String checkValue) {
        boolean flag = false;
        try {
            Connection connectionPostgres = DriverManager.getConnection(storage.getValue(checkValue), storage.getValue("user"), storage.getValue("password"));
            if (connectionPostgres != null) {
                connectionPostgres.close();
                flag = true;
            }
        } catch (SQLException e) {
            log.warn("Problem with connect to Postges", e);
        }
        return flag;
    }

    public void createDataBase(String dbName) {
        try {
            Connection con = DriverManager.getConnection(storage.getValue("pathDB"), storage.getValue("user"), storage.getValue("password"));
            String request = "create database " + dbName;
            Statement statement = con.createStatement();
            statement.execute(request);
            statement.close();
            con.close();
            createTable(storage.getValue("tableName"));
        } catch (SQLException e) {
            log.warn("Problem with create DataBase. ", e);
        }
    }

    public void createTable(String tableName) {
        PreparedStatement pst;
        try {
            Connection fullCon = DriverManager.getConnection(storage.getValue("fullPath"), storage.getValue("user"), storage.getValue("password"));
            pst = fullCon.prepareStatement("create table " +
                    tableName +
                    " (id serial primary key, name character varying (300), autor character varying(300),  create_time timestamp, url character varying (3000))");
            pst.execute();
            pst.close();
            fullCon.close();
        } catch (SQLException e) {
            log.warn("Problem with create Table in DataBase", e);
        }
    }

    public boolean checkTableExists(String tableName) {
        Statement statement;
        boolean flag = false;
        try {
            Connection fullCon = DriverManager.getConnection(storage.getValue("fullPath"), storage.getValue("user"), storage.getValue("password"));
            statement = fullCon.createStatement();
            ResultSet rst = statement.executeQuery("select count(*) from " + tableName);
            int columnCount = rst.getMetaData().getColumnCount();
            if (columnCount > 0) {
                flag = true;
            }
            rst.close();
            statement.close();
            fullCon.close();
        } catch (PSQLException e1) {
           log.warn("Table is epsent", e1);
        } catch (SQLException e) {
            log.warn("Table is epsent", e);
        }
        return flag;
    }

    public void insertToDB(ItemSQL item) {
        PreparedStatement pst;
        try {
            Connection fullCon = DriverManager.getConnection(storage.getValue("fullPath"), storage.getValue("user"), storage.getValue("password"));
            pst = fullCon.prepareStatement("insert into " + storage.getValue("tableName") + " (name, autor, create_time, url) values (?, ?, ?, ?)");
            pst.setString(1, item.getName());
            pst.setString(2, item.getAutor());
            pst.setTimestamp(3, item.getTime());
            pst.setString(4, item.getUrl());
            pst.executeUpdate();
            pst.close();
            fullCon.close();
        } catch (SQLException e) {
            log.warn("Problem with insert data in table. ", e);
        }
    }

    public Timestamp lastRecordTime() {
        Timestamp timestamp = null;
        Statement statement;
        try {
            Connection fullCon = DriverManager.getConnection(storage.getValue("fullPath"), storage.getValue("user"), storage.getValue("password"));
            statement = fullCon.createStatement();
            ResultSet resultSet = statement.executeQuery("select max(create_time) from vacansion");
            while (resultSet.next()) {
                timestamp = resultSet.getTimestamp(1);
            }
        } catch (SQLException e) {
            log.warn("Table is epsent", e);
        }
        return timestamp;
    }
}