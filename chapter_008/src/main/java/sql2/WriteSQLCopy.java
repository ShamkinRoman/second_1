package sql2;

import java.sql.*;

public class WriteSQLCopy {


    private Connection connection;

    public WriteSQLCopy(Connection connection) {
        this.connection = connection;
    }

    public void setCreateTable() {
        this.createTable();
    }

    public void setOpenTable() {
        this.open();
    }

    public void setCloseTable() {
        this.close();
    }

    public void setInsertTable() {
        this.insert();
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


    private void insert() {
        PreparedStatement statement = null;

        try {
            for (int i = 1; i < 1_000_001; i++) {


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

            connection.setAutoCommit(false);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
