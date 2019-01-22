package crudServlet;

import org.apache.commons.dbcp2.BasicDataSource;


import java.sql.*;
import java.util.Collection;
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
    private Map<String, String> setting;
    private final String tableName;
    private final String passTable;

    public DBStore getINSTANCE() {
        return INSTANCE;
    }

    public DBStore() {
        this.setting = new DataSetup().getMap();
        this.tableName = setting.get("jspTable");
        this.passTable = setting.get("passTable");
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl(setting.get("fullPathJSP"));
        SOURCE.setUsername(setting.get("user"));
        SOURCE.setPassword(setting.get("password"));
        SOURCE.setMinIdle(3);
        SOURCE.setMaxIdle(5);
        SOURCE.setMaxOpenPreparedStatements(100);
        createTable(this.tableName);
        createPasswordRoleTable("rolePassword");
    }

    public void createPasswordRoleTable(String tablePassword) {
        PreparedStatement pst;
        try (Connection connection = SOURCE.getConnection()) {
            String request = String.format("create table if not exists %s (id serial primary key, login character varying (300), " +
                    "foreign key(login) references %s(login) on delete cascade on update cascade, UNIQUE(login), " +
                    "password character varying(255), role character varying(80));", tablePassword, this.tableName);
            pst = connection.prepareStatement(request);
            pst.execute();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        PreparedStatement pst;
        try (Connection connection = SOURCE.getConnection()) {
            String request = String.format("create table if not exists %s (id serial primary key, name character varying (300), " +
                    "login character varying(300), email character varying(300),  dataCreate character varying(300), UNIQUE(login), UNIQUE(email));", tableName);
            pst = connection.prepareStatement(request);
            pst.execute();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(User user) {
        boolean result = false;
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
            result = true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 0) {
                System.out.println(String.format("++++++++++ block add user ++++++++++++++"));
                System.out.println(String.format("++User must have uniq login and e-mail++"));
                System.out.println(user.toString());
                System.out.println(String.format("++++++++++++++++++++++++++++++++++++++++"));
            } else e.printStackTrace();
        }
        return result;
    }


    public boolean addPasswordRole(User user, String password, String role) {
        boolean result = false;
        PreparedStatement pst;
        if (add(user)) { // in this condition call function ADD and adding user in storage.
            String req = String.format("insert into %s (login, password, role) values (?, ?, ?);", passTable);
            try (Connection con = SOURCE.getConnection()) {
                pst = con.prepareStatement(req);
                pst.setString(1, user.getLogin());
                pst.setString(2, password);
                pst.setString(3, role);
                pst.executeUpdate();
                pst.close();
                result = true;
            } catch (SQLException e) {
                if (e.getErrorCode() == 0) {
                    System.out.println(String.format("++++++++++ block add user and password++++++++++++++"));
                    System.out.println(String.format("++User must have uniq login and e-mail++++++++++++++"));
                    System.out.println(user.toString());
                    System.out.println(String.format("++++++++++++++++++++++++++++++++++++++++++++++++++++"));
                } else e.printStackTrace();
            }
        }
        return result;
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
            if (e.getErrorCode() == 0) {
                System.out.println(String.format("++++++++++ block makeRequest +++++++++++"));
                System.out.println(String.format("++User must have uniq login and e-mail++"));
                System.out.println(request);
                System.out.println(String.format("++++++++++++++++++++++++++++++++++++++++"));
                e.printStackTrace();
            } else e.printStackTrace();
        }
        return result;
    }

    public String isCheckPass(String login, String password) {
        String result = "99";
        try (Connection con = SOURCE.getConnection()) {
            String request = String.format("select * from %s where login = '%s';", passTable, login);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(request);
            if (rs.next()) {
                if (rs.getString("password").equals(password)) {
                    result = rs.getString("role");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getRolebyLogin(String login) {
        String result = "99";
        try (Connection con = SOURCE.getConnection()) {
            String request = String.format("select * from %s where login = '%s';", passTable, login);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(request);
            if (rs.next()) {
                result = rs.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getRolebyId(String id) {
        String result = "99";
        try (Connection con = SOURCE.getConnection()) {
            String login = String.format("select * from %s where id = %s;", tableName, id);
            Statement st = con.createStatement();
            ResultSet rs;
            rs = st.executeQuery(login);
            if (rs.next()) {
                result = rs.getString("login");
            }
            String request = String.format("select * from %s where login = '%s';", passTable, result);
            rs = st.executeQuery(request);
            if (rs.next()) {
                result = rs.getString("role");
            }
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