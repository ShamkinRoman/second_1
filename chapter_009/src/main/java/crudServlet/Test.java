package crudServlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class Test {
    private static Map<String, String> map = new DataSetup().getMap();

    public static void main(String[] args) {

        try (Connection con =  DriverManager.getConnection(map.get("fullPathJSP1"), map.get("user"), map.get("password"))){
            System.out.println("TRUE");
        }catch (SQLException e) {
            System.out.println("NOT");
            e.printStackTrace();
        }
    }
}
