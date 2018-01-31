package sql;

import java.sql.*;
import java.util.Scanner;

public class jdbc20459 {

    public static void main(String[] args) {

        jdbc20459 prog = new jdbc20459();
        prog.open();
        prog.insert();
        prog.close();
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

    Connection co;

    private void close() {
        try {
            co.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private void open() {
        try {
            co = DriverManager.getConnection("jdbc:sqlite:D://sqlite//newData.db");
            System.out.println("--------------");
            System.out.println("-Connected on-");
            System.out.println("--------------");
            System.out.println();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
