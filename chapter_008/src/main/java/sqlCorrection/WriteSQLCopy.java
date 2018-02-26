package sqlCorrection;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.*;
import java.util.HashMap;

/*
Class for create DB and table in DB.
 */
public class WriteSQLCopy {

    private Connection con;
    private int numer;

    public void start() {
        open();
        createTable();
        insert();
        close();
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    public void setCon(String connection) {
        try {
            con = DriverManager.getConnection(connection);
            System.out.println("Connect module WriteSQL to DB successfully.");
        } catch (SQLException e) {
            System.out.println("Error to make connection on DB");
            System.out.println("Check you system on SQLite. --> Не обнаружена SQLite, проверьте ее наличие.");
            System.out.println("Not detect folder for DB.  ---> Проверьте наличие директории указанной в config.sys");
            PrintStream st = null;
            try {
                st = new PrintStream(new FileOutputStream("logWriteSQL.txt"));
                System.out.println("Check file ==> logWriteSQL.txt <== ");
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            System.setErr(st);
            System.setOut(st);
            e.printStackTrace();
            st.close();
        }
    }

    private void open() {
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() {
        Statement statement = null;
        try {
            statement = con.createStatement();
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
            con.setAutoCommit(true);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insert() {
        PreparedStatement statement = null;
        try {
            for (int i = 1; i <= numer; i++) {
                statement = con.prepareStatement("insert into TEST (numer) values (?)");
                statement.setInt(1, i);
                statement.executeUpdate();
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
