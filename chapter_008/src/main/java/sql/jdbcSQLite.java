package sql;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.Scanner;

public class jdbcSQLite {

    private Connection connection;


    public static void main(String[] args) {
        long timeStart=System.currentTimeMillis();
        jdbcSQLite jdbcSQLite = new jdbcSQLite();
        jdbcSQLite.openDataBase();
        System.out.println(jdbcSQLite.checkExistsTable());

       // jdbcSQLite.checkSizeTable(6);

      //  jdbcSQLite.createTable();
        jdbcSQLite.insert(666);
        jdbcSQLite.autoInsert();

        jdbcSQLite.close();
        long time=System.currentTimeMillis();
        System.out.println( String.format("Time exists is %s second",( System.currentTimeMillis()- timeStart )/1000) );

    }

    private void openDataBase() {

        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:d://sqlite//newData.db");
            connection.setAutoCommit(false);
            System.out.println("-----------------");
            System.out.println("--Connection on--");
            System.out.println("-База подключена-");
            System.out.println("-----------------");
            System.out.println();

        } catch (SQLException e) {
            //
            //error №1
            //
            e.printStackTrace();
            System.out.println("----ALERT---код ошибки №01----Не удалось подключиться к заданной базе данных-----");

        }

    }

    private int checkExistsTable() {
        int sizeRecord = 0;
        try {

            Statement statement = connection.createStatement();


            try {
                ResultSet result = statement.executeQuery("select count(*) from TEST");

              sizeRecord = result.getInt(1);
            } catch (SQLException e) {
                //error №03
                // e.printStackTrace();
                System.out.println("Таблица Тест не существует в базе данных. Table TEST not exists in DB.  Place №03 ");
            }

        } catch (SQLException e) {
            //error 04
            e.printStackTrace();
            System.out.println("Не удалось выполнить проверку размера таблицы ТЕСТ. Fail querry of size table TEST. Error №04 ");
            System.out.println("");
        }
        return sizeRecord;
    }


    private void checkSizeTable(int N) {


    }

    private void createTable() {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeQuery("create table if not exists TEST (id integer primary key AUTOINCREMENT, numer integer)");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void insert(int N) {

        PreparedStatement preparedStatement = null;

        try {
            for (int i = 1; i < N + 1; i++) {


                preparedStatement = connection.prepareStatement("insert into TEST (numer) values (?)");
                preparedStatement.setInt(1, i);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void autoInsert() {

    }


    private void close() {
        try {
            connection.close();
            System.out.println("--------------------");
            System.out.println("--Connection close--");
            System.out.println("--------------------");
        } catch (SQLException e) {
            //
            // error №2
            //

            e.printStackTrace();
            System.out.println("----ALERT---код ошибки №02----Не удалось закрыть соединение к базе данных-----");


        }
    }

}
