package crudServlet;

import org.apache.commons.dbcp2.BasicDataSource;


import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class for storage User attributes. (examples class User storage this.).
 * You must correction String URL for you database.
 * You also can be correction tableName for comfortable create table and use it later.
 */
public class DBStore implements Store, AutoCloseable {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DBStore INSTANCE = new DBStore();
    //    private Map<Integer, User> storage = new ConcurrentHashMap<>();
    private final String tableName = "jspTable";
    private final String url = "jdbc:postgresql://localhost:5432/crud";
    private final String userName = "postgres";
    private final String passwors = "";

    public Integer giveId() {
        Integer result = -99;
        Statement st;
        String request = String.format("select max(id) from %s;", tableName);
        try {
            Connection con = SOURCE.getConnection();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(request);
            if (rs.next()) {
                result = rs.getInt(1);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ++result;
    }

    public DBStore getINSTANCE() {
        return INSTANCE;
    }

    public DBStore() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl(this.url);
        SOURCE.setUsername(this.userName);
        SOURCE.setPassword(this.passwors);
        SOURCE.setMinIdle(3);
        SOURCE.setMaxIdle(5);
        SOURCE.setMaxOpenPreparedStatements(100);
        createTable(this.tableName);
    }

    public void createTable(String tableName) {
        PreparedStatement pst;
        try (Connection connection = SOURCE.getConnection()) {
            String request = String.format("create table if not exists %s (id serial primary key, name character varying (300), " +
                    "login character varying(300), email character varying(300),  dataCreate character varying(300));", tableName);
//            pst = connection.prepareStatement("create table if not exists  " +
//                    tableName +
//                    " (id serial primary key, idUser integer, name character varying (300), login character varying(300), email character varying(300),  dataCreate character varying(300));");
            pst = connection.prepareStatement(request);
            pst.execute();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(User user) {
        PreparedStatement pst;
        String request = String.format("insert into %s (name, login, email, dataCreate) values (?, ?, ?, ?);", tableName);
        try (Connection con = SOURCE.getConnection()) {
            pst = con.prepareStatement(request);
            pst.setString(1, user.getName());
            pst.setString(2, user.getLogin());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getDataCreate());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(User user) {
        String request = String.format("update %s SET name='%s', login='%s', email='%s' where id=%s;", tableName, user.getName(), user.getLogin(), user.getEmail(), user.getId());
        return makeRequest(request);
    }

    @Override
    public boolean delete(User user) {
        String request = String.format("delete from %s where id=%s;", tableName, user.getId());
        return makeRequest(request);
    }

    private boolean makeRequest(String request) {
        boolean result = false;
        try (Connection con = SOURCE.getConnection()) {
            Statement st = con.createStatement();
            st.executeUpdate(request);
            st.close();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public Map<Integer, User> findAllInMap() {
        Map<Integer, User> storage = new ConcurrentHashMap<>();
        Statement st;
        String request = String.format("select * from %s;", tableName);
        try (Connection con = SOURCE.getConnection()) {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(request);
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String login = rs.getString("login");
                String email = rs.getString("email");
                storage.put(id, new User(id, name, login, email));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return storage;
    }

    @Override
    public void close() throws Exception {
        SOURCE.close();
    }
}