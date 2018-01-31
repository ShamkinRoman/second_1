package sql;

import java.sql.*;
import java.util.Scanner;

public class jdbc20459 {

    Connection co;

    public static void main(String[] args) {

        jdbc20459 prog = new jdbc20459();
        prog.open();
        prog.createTable();
     //   prog.insert();
        prog.autoInsert();
        prog.close();
    }

    private void autoInsert() {

       // String querry = "insert into users (name,phone) values ('aaaa', '55556')";

        try {
           // Statement st = co.createStatement();
          //  st.executeUpdate(querry);

            for (int i = 0; i < 6; i++) {

                PreparedStatement res = co.prepareStatement("insert into users (name,phone) values (?, ?)");
                res.setString(1, String.format("name is %s", i));
                res.setString(2, String.format("номер tel. %s", i));
                res.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void createTable() {

        Statement rezult = null;
        try {

            rezult = co.createStatement();
            rezult.execute("create table if not exists users (id integer primary key AUTOINCREMENT, name varchar(255), phone varchar(255))");
           // ResultSet rez = rezult.executeQuery("create table if not exists users (id integer primary key AUTOINCREMENT, name varchar(255), phone varchar(255))");

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    private void insert() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите имя:  ");
        String name = scanner.nextLine();

        System.out.print("Введите телефон:   ");
        String phone = scanner.nextLine();

        PreparedStatement st = null;

        try {
            st = co.prepareStatement("insert into users (name, phone)  values (?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, name);
            st.setString(2, phone);
            st.executeUpdate();

            ResultSet generatedKey = st.getGeneratedKeys();

            if (generatedKey.next()) {
                System.out.println(generatedKey.getInt(1));
            }

            Statement rezult = co.createStatement();
            ResultSet rez = rezult.executeQuery("select count(*) from users");

            while(rez.next()) {
                System.out.println(String.format("Число записей равно %s", rez.getInt(1)));

            }
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    private void close() {
        try {
            co.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private void open() {
        try {
            co = DriverManager.getConnection("jdbc:sqlite:c://sql//newData.db");
            System.out.println("--------------");
            System.out.println("-Connected on-");
            System.out.println("--------------");
            System.out.println();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
