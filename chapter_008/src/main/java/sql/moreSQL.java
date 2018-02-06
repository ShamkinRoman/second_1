package sql;

import java.sql.*;

public class moreSQL {
    private Connection connection;

    public static void main(String[] args) {


        moreSQL moreSQL = new moreSQL();

        long startTime = System.currentTimeMillis();

        moreSQL.open();
        moreSQL.createTable();
        moreSQL.insert();
        //  moreSQL.print();

        moreSQL.close();

        System.out.println(String.format("Время выполнения составляет %s", (System.currentTimeMillis() - startTime) / 1000));

    }

    private void createTable() {
        Statement statement = null;

        try {
            statement = connection.createStatement();
            statement.execute("create table if not exists TEST (id integer primary key AUTOINCREMENT, numer integer)");
            statement.execute("delete from TEST");



        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void close() {
        try {
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void print() {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from TEST");

            while (resultSet.next()) {
                System.out.println(String.format(" %s    %s", resultSet.getInt("id"), resultSet.getInt("numer")));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void insert() {
        PreparedStatement statement = null;

        try {
            for (int i = 0; i < 2_010; i++) {


                statement = connection.prepareStatement("insert into TEST (numer) values (?)");
                statement.setInt(1, i);
                statement.executeUpdate();
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void open() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:d://sqlite//newData.db");
            connection.setAutoCommit(false);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
