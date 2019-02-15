package cinemaServlet;

import crudServlet.DataSetup;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Class for storage place and user.
 * <p>
 * You also can be correction account for comfortable create table and use it later.
 */
public class CinemaDBStore implements AutoCloseable {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final CinemaDBStore INSTANCE = new CinemaDBStore();
    private Map<String, String> setting;
    private final String account;
    private final String halls;

    public CinemaDBStore getINSTANCE() {
        return INSTANCE;
    }

    public CinemaDBStore() {
        this.setting = new DataSetup().getMap();
        this.account = setting.get("account");
        this.halls = setting.get("halls");
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl(setting.get("fullPathJSP"));
        SOURCE.setUsername(setting.get("user"));
        SOURCE.setPassword(setting.get("password"));
        SOURCE.setMinIdle(3);
        SOURCE.setMaxIdle(5);
        SOURCE.setMaxOpenPreparedStatements(100);
        createTable(this.account);
        createTableHalls(this.halls);
    }

    public void createTableHalls(String halls) {
        PreparedStatement pst;
        try (Connection connection = SOURCE.getConnection()) {
            String request = String.format("create table if not exists %s (id serial primary key, place character varying (20), UNIQUE(place), " +
                    "name character varying (300), foreign key(name) references %s(name) on delete cascade on update cascade);", halls, this.account);
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
            String request = String.format("create table if not exists %s (id serial primary key, name character varying (300), UNIQUE(name)," +
                    "phone character varying(300));", tableName);
            pst = connection.prepareStatement(request);
            pst.execute();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addBueyr(Buyer buyer) {
        PreparedStatement pst;
        String request = String.format("insert into %s (name, phone) values (?, ?);", this.account);
        try (Connection con = SOURCE.getConnection()) {
            pst = con.prepareStatement(request);
            pst.setString(1, buyer.getName());
            pst.setString(2, buyer.getPhone());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            SQLError(e, buyer);
        }
    }

    public boolean addPlace(Buyer buyer) {
        boolean result = false;
        PreparedStatement pst;
        Statement st;
        ResultSet rst;
        int i = 99;
        addBueyr(buyer);
        String addPlaceString = String.format("insert into %s (place, name) values (?, ?);", this.halls);
        String freePlace = String.format("select count(*) place from %s where place = '%s';", this.halls, buyer.getPlace());
        String isolationTransaction = "BEGIN TRANSACTION ISOLATION LEVEL SERIALIZABLE;";
        String commit = "commit;";
        try (Connection con = SOURCE.getConnection()) {
            con.setAutoCommit(false);
            st = con.createStatement();
            st.executeUpdate(isolationTransaction);
            rst = st.executeQuery(freePlace);
            if (rst.next()) {
                i = Integer.parseInt(rst.getString("place"));
            }
            if (i == 0) {
                pst = con.prepareStatement(addPlaceString);
                pst.setString(1, buyer.getPlace());
                pst.setString(2, buyer.getName());
                pst.executeUpdate();
                pst.close();
                result = true;
            } else {
                st.executeUpdate("ROLLBACK;");
            }
            st.executeUpdate(commit);  // Нужно ли здесь это прописывать или после 108 строки ?
            st.close();
            con.commit();
        } catch (SQLException e) {
            SQLError(e, buyer);
        }
        return result;
    }

    private void SQLError(SQLException e, Buyer buyer) {
        if (e.getErrorCode() == 0) {
            System.out.println(String.format("++++++++++ block add user and password++++++++++++++"));
            System.out.println(String.format("++User must have uniq login and e-mail++++++++++++++"));
            System.out.println(buyer.toString());
            System.out.println(String.format("++++++++++++++++++++++++++++++++++++++++++++++++++++"));
        } else e.printStackTrace();
    }

    public boolean deletePlace(String place) {
        String request = String.format("delete from %s where place='%s';", this.halls, place);
        return makeRequest(request);
    }

    private boolean makeRequest(String request) {
        boolean result = false;
        try (Connection con = SOURCE.getConnection()) {
            con.setAutoCommit(false);
            Statement st = con.createStatement();
            st.executeUpdate(request);
            st.close();
            con.commit();
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

    public List<String> getbusyPlace() {
        List<String> list = new ArrayList<>();
        String requestPlace = String.format("Select place from %s;", this.halls);
        try (Connection con = SOURCE.getConnection()) {
            con.setAutoCommit(false);
            Statement st = con.createStatement();
            ResultSet rs;
            rs = st.executeQuery(requestPlace);
            while (rs.next()) {
                list.add(rs.getString("place"));
            }
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void close() throws Exception {
        SOURCE.close();
    }
}