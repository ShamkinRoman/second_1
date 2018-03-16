package parserSQL;

import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.List;

public class CheckConnectToDataBase {
    Storage storage = new Storage();
    public static void main(String[] args) {
        new CheckConnectToDataBase().check();
    }

    public void check() {
        boolean flagPostgres = connectToPostgres("pathDB");
        boolean flagDataBaseExists = connectToPostgres("fullPath");
        System.out.println(flagDataBaseExists && flagPostgres);
        if (!flagDataBaseExists) {
            createDataBase(storage.getValue("dbName"));
        }
        if (!checkTableExists(storage.getValue("tableName"))) {
            createTable(storage.getValue("tableName"));
        }

        SQLruParser sqLruParser = new SQLruParser();
        sqLruParser.start();
        List<ItemSQL> list = sqLruParser.getItemSQLList();
        list.forEach(value->{
            insertToDB(value);
        });

        System.out.println(String.format(" SSS ==== %s", lastRecordTime()));
    }

    public boolean connectToPostgres(String checkValue) {
        boolean flag = false;
        try {
            Connection connectionPostgres = DriverManager.getConnection(storage.getValue(checkValue), storage.getValue("user"), storage.getValue("password"));
            if (connectionPostgres != null) {
                connectionPostgres.close();
                flag = true;
            }
            System.out.println("Connection ok!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public void createDataBase(String dbName) {
        try {
            Connection con = DriverManager.getConnection(storage.getValue("pathDB"), storage.getValue("user"), storage.getValue("password"));
            System.out.println("DB is epsent, create DB. ---> База данных отсутствует, создаю базу данных.");
            String request = "create database " + dbName;
            Statement statement = con.createStatement();
            statement.execute(request);
            statement.close();
            System.out.println(String.format("DB with name %s created. ---> База данных с именем %s создана.", dbName, dbName));
            con.close();

            createTable(storage.getValue("tableName"));

        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
        } catch (PSQLException e1){
            System.out.println("DFF");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
    public void insertToDB(ItemSQL item) {
        PreparedStatement pst;
        try {
            Connection fullCon = DriverManager.getConnection(storage.getValue("fullPath"), storage.getValue("user"), storage.getValue("password"));
            pst = fullCon.prepareStatement("insert into "+storage.getValue("tableName")+ " (name, autor, create_time, url) values (?, ?, ?, ?)");
            pst.setString(1, item.getName());
            pst.setString(2, item.getAutor());
            ConvertTime convertTime = new ConvertTime(item);
            pst.setTimestamp(3, convertTime.start());
            pst.setString(4,item.getUrl());
            pst.executeUpdate();
            pst.close();
            fullCon.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Timestamp lastRecordTime(){
        Timestamp timestamp=null;
        Statement statement;
        try {
            Connection fullCon = DriverManager.getConnection(storage.getValue("fullPath"), storage.getValue("user"), storage.getValue("password"));
            statement=fullCon.createStatement();
            ResultSet resultSet = statement.executeQuery("select max(create_time) from vacansion");
            while (resultSet.next()){
                timestamp=resultSet.getTimestamp(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timestamp;
    }
}