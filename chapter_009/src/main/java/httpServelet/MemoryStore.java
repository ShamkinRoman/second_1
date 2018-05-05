package httpServelet;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class MemoryStore {
    Properties properties;

    public MemoryStore(Properties properties) {
        this.properties = properties;
    }

    public void add(User user) {
        PreparedStatement pst;
        try {
            Connection con = DriverManager.getConnection(properties.getProperty("fullPath"), properties.getProperty("user"), properties.getProperty("password"));
            pst = con.prepareStatement("insert into " + properties.getProperty("tableName") + " (name, login, email, create_time) values (?, ?, ?, ?)");
            pst.setString(1, user.getName());
            pst.setString(2, user.getLogin());
            pst.setString(3, user.getEmail());
            pst.setTimestamp(4, user.getDateCreate());
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void update(User user) {

    }
}

