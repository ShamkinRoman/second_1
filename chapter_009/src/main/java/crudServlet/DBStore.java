package crudServlet;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class DBStore implements Store {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static DBStore INSTANCE = new DBStore();
    private boolean flagCreateTable = false;
    private final String tableName = "jspTable";
    private final String dataBaseName = "jspDataBase";
    private Integer id = 0;

    public Integer giveId() {
        return id++;
    }

    public static DBStore getINSTANCE() {
        return INSTANCE;
    }

    public DBStore() {
        String userName = "postgres";
        String passwors = "";
        String url = "jdbc:postgresql:/localhost:5432";
        SOURCE.setUrl(url);
        SOURCE.setUsername(userName);
        SOURCE.setPassword(passwors);
        SOURCE.setMinIdle(3);
        SOURCE.setMaxIdle(5);
        createDataBase(dataBaseName);
    }


    public void createDataBase(String dbName) {
        try {
            Connection con = SOURCE.getConnection();
            String request = "create database " + dbName;
            Statement statement = con.createStatement();
            statement.execute(request);
            statement.close();
            con.close();
            createTable(tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        PreparedStatement pst;
        try {
            Connection connection = SOURCE.getConnection();
            flagCreateTable = true;
            pst = connection.prepareStatement("create table " +
                    tableName +
                    " (id serial primary key, idUser integer, name character varying (300), login character varying(300), email character varying(300),  dataCreate character varying(300))");
            pst.execute();
            pst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(User user) {
        PreparedStatement pst;
        String request = String.format("insert into %s (idUser, name, login, email, dataCreate) values (?, ?, ?, ?, ?);", tableName);


        try {
            Connection con = SOURCE.getConnection();
            pst = con.prepareStatement(request);
            pst.setInt(1, user.getId());
            pst.setString(2, user.getName());
            pst.setString(3, user.getLogin());
            pst.setString(4, user.getEmail());
            pst.setString(5, user.getEmail());
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(User user) {
        boolean result = false;
        Statement st;
        String requst = String.format("update %s SET name='%s', login='%s', email='%s' where idUser='%s'", tableName, user.getName(), user.getLogin(), user.getEmail(), user.getId());

        try {
            Connection con = SOURCE.getConnection();
            st = con.createStatement();
            st.executeUpdate(requst);
            st.close();
            con.close();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(User user) {
        return false;
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
        return null;
    }
}
