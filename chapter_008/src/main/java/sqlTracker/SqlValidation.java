package sqlTracker;

import java.sql.*;

public class SqlValidation {

    private boolean dbFlagExists = false;
    private Connection con = null;
    private Connection fullCon = null;
    private String url = "jdbc:postgresql://localhost:5432/";
    private String userName = "postgres";
    private String password = "Uht,tymrjdf42";
    private String databaseName = "ddzz";

    public SqlValidation(String url, String userName, String password, String databaseName) {
        this.url = url;
        this.userName = userName;
        this.password = password;
        this.databaseName = databaseName;
    }

    public SqlValidation() {
    }

    public void go() {

        start();
        if (!checkTableExists()) {
            createTable();
        }
    }

    /**
     * Connecting to DB.
     */
    public void start() {
        try {
            Statement statement = null;
            con = DriverManager.getConnection(url, userName, password);
            statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from pg_database");
            while (resultSet.next()) {
                if (resultSet.getString(1).equals(databaseName)) {
                    dbFlagExists = true;
                    break;
                }
            }
            if (dbFlagExists == false) {
                createDataBase();
            } else {
                System.out.println("Connect to database. ---> Подключаюсь к базе данных");
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println("You have a problem to connect DB. Check validate.  ---> Проверьте корректность данных для подключения.");
            e.printStackTrace();
        }
    }

    public void createDataBase() {
        try {
            System.out.println("DB is epsent, create DB. ---> База данных отсутствует, создаю базу данных.");
            String request = "create database " + databaseName;
            Statement statement = con.createStatement();
            statement.execute(request);
            statement.close();
            System.out.println(String.format("DB with name %s created. ---> База данных с именем %s создана.", databaseName, databaseName));

            createTable();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void createTable() {
        System.out.println("Table Item is epsent, create table. ---> Таблица Item отсутствует, создаю таблицу Item.");
        Statement statement = null;
        try {
            fullCon = DriverManager.getConnection(url + databaseName, userName, password);
            statement = fullCon.createStatement();
            statement.execute("create table items(id serial primary key, name character varying (300), description character varying (300), create_time timestamp)");
            System.out.println("Table items created. ---> Таблица Item создана.");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkTableExists() {
        boolean flag = false;
        try {
            if (fullCon == null) {
                fullCon = DriverManager.getConnection(url + databaseName, userName, password);
            }
            Statement statement = fullCon.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from pg_tables where tablename='items'");
            while (resultSet.next()) {
                flag = true;
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(flag ? "Table items exists. ---> Таблица items существует" : "Table items NOT exists. ---> Таблица items отсутствует");
        return flag;
    }

    public void close() {
        try {
            con.close();
            fullCon.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getFullCon() {
        return fullCon;
    }
}