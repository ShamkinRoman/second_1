package crudServlet;

import org.apache.commons.dbcp2.BasicDataSource;


import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DBStore implements Store {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DBStore INSTANCE = new DBStore();
    private Map<Integer, User> storage = new ConcurrentHashMap<>();
    private boolean flagCreateTable = false;
    private final String tableName = "jspTable";
    private final String dataBaseName = "jspDataBase";

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
        String userName = "postgres";
        String passwors = "";
        String url = "jdbc:postgresql://localhost:5432/crud";
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl(url);
        SOURCE.setUsername(userName);
        SOURCE.setPassword(passwors);
        SOURCE.setMinIdle(3);
        SOURCE.setMaxIdle(5);
        SOURCE.setMaxOpenPreparedStatements(100);
    }


    public void createDataBase() {
        try {
            boolean dataBase = false;
            Connection con = SOURCE.getConnection();
            String request = String.format("create database %s;", dataBaseName);
            String existsDatabase = String.format("select from pg_database where datname='%s';", dataBaseName);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(existsDatabase);
            if (rs.next()) {
                System.out.println("++++++++++++++");
                System.out.println( dataBase = rs.getInt(1) == 1 ? true : false );
                System.out.println("++++++++++++++");

            }

            if (!dataBase) {
                String createDatabase = String.format("create database %s;", dataBaseName);
                st.executeUpdate(createDatabase);
                SOURCE.setUrl(String.format("jdbc:postgresql://localhost:5432/%s;", dataBaseName));
                createTable(tableName);
            } else {
                SOURCE.setUrl(String.format("jdbc:postgresql://localhost:5432/%s;", dataBaseName));
            }
            rs.close();
            st.close();
            con.close();
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
                    " (id serial primary key, idUser integer, name character varying (300), login character varying(300), email character varying(300),  dataCreate character varying(300));");
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
        String requst = String.format("update %s SET name='%s', login='%s', email='%s' where idUser=%s;", tableName, user.getName(), user.getLogin(), user.getEmail(), user.getId());

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
        boolean result = false;
        Statement st;
        String request = String.format("delete from %s where idUser=%s;", tableName, user.getId());
        try {
            Connection con = SOURCE.getConnection();
            st = con.createStatement();
            st.executeUpdate(request);
            st.close();
            con.close();
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
        Statement st;
        String request = String.format("select * from %s;", tableName);
        try {
            Connection con = SOURCE.getConnection();
            st = con.createStatement();
            ResultSet rs = st.executeQuery(request);
            storage.clear();
            while (rs.next()) {
                Integer idUser = rs.getInt("idUser");
                String name = rs.getString("name");
                String login = rs.getString("login");
                String email = rs.getString("email");
                storage.put(idUser, new User(idUser, name, login, email));
            }
            rs.close();
            st.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return storage;
    }
}
