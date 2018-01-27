package sql;

import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

import java.sql.*;
import java.util.Properties;

public class mavenJDBCtest {
   private static final Logger log = LoggerFactory.getLogger(mavenJDBCtest.class);

    public static void main(String[] args) {
        System.out.println("axaxa-xa");
        String url = "jdbc:postgresql://localhost:5432/shamkinromancar";
        String user = "postgres";
        String password = "Uht,tymrjdf42";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            PreparedStatement st =conn.prepareStatement("insert into body (name) values (?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, "new cargo 3");
            st.executeUpdate();

            ResultSet generatedKey = st.getGeneratedKeys();

            if (generatedKey.next()) {
                System.out.println(generatedKey.getInt(1));
            }

            generatedKey.close();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn!=null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                   log.error(e.getMessage(), e);
                }
            }
        }



        /* Properties props = new Properties();
        props.setProperty("user", "fres");
        props.setProperty("password", "secret");
        props.setProperty("ssl", "true");

        try {
            Connection conn = DriverManager.getConnection(url, props);

            String url1 = "jdbc:postgresql://localhost/test?user=fred&password=secret&ssl=true";
            Connection con1 = DriverManager.getConnection(url1);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

    }

}
