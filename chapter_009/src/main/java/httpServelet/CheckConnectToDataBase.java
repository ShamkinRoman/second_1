package httpServelet;

import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/*
 * Class for cheking exists DataBase, Table, Adding new records in DataBase.
 */
public class CheckConnectToDataBase {
    private Properties properties;
    private static final Logger log = LoggerFactory.getLogger(CheckConnectToDataBase.class.getName());

    private void fill() {
        URL urlToConfigFile = this.getClass().getClassLoader().getResource("sqlConfig.properties");
        String fileProperties = "";
        properties = new Properties();
        FileInputStream fileInputStream;
        try {
            if (urlToConfigFile != null) {
                fileProperties = urlToConfigFile.getFile();
            }
            properties.load(new FileInputStream(fileProperties));
        } catch (IOException e) {
            log.warn(String.format("Error wasn't loaded with properties like {}", properties, e));
        }
    }

    public void init() {
        fill();
        if (!connectToPostgres(properties.getProperty("pathDB"))) {
            log.warn(String.format("I not found POSTGRES or check port to connect in sqlConfig. "));
        }
        if (!connectToPostgres(properties.getProperty("fullPath"))) {
            log.warn(String.format("DataBase is not create. I start creating her. "));
            createDataBase();
        }
        if (!checkTableExists()) {
            log.warn(String.format("I not found table. I creating table "));
            createTable();
        }
    }

    private boolean connectToPostgres(String path) {
        boolean flag = false;
        try {
            Connection connectionPostgres = DriverManager.getConnection(properties.getProperty(path), properties.getProperty("user"), properties.getProperty("password"));
            if (connectionPostgres != null) {
                connectionPostgres.close();
                flag = true;
            }
        } catch (SQLException e) {
            log.warn("Problem with connect to Postges", e);
        }
        return flag;
    }

    private void createDataBase() {
        try {
            Connection con = DriverManager.getConnection(properties.getProperty("pathDB"), properties.getProperty("user"), properties.getProperty("password"));
            String request = "create database " + properties.getProperty("dbName");
            Statement statement = con.createStatement();
            statement.execute(request);
            statement.close();
            con.close();
        } catch (SQLException e) {
            log.warn("Problem with create DataBase. ", e);
        }
    }

    private void createTable() {
        PreparedStatement pst;
        try {
            Connection fullCon = DriverManager.getConnection(properties.getProperty("fullPath"), properties.getProperty("user"), properties.getProperty("password"));
            pst = fullCon.prepareStatement("create table " +
                    properties.getProperty("tableName") +
                    " (id serial primary key, name character varying (300), login character varying(300), email character varying(300), create_time timestamp)");
            pst.execute();
            pst.close();
            fullCon.close();
        } catch (SQLException e) {
            log.warn("Problem with create Table in DataBase", e);
        }
    }

    private boolean checkTableExists() {
        Statement statement;
        boolean flag = false;
        try {
            Connection fullCon = DriverManager.getConnection(properties.getProperty("fullPath"), properties.getProperty("user"), properties.getProperty("password"));
            statement = fullCon.createStatement();
            ResultSet rst = statement.executeQuery("select count(*) from " + properties.getProperty("tableName"));
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

    public boolean isExistsTable() {
        return checkTableExists();
    }

    public Properties getProperties() {
        return properties;
    }
}