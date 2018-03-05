package sqlTracker;

import java.sql.*;

/**
 * Created by Администратор on 23.05.2017.
 */
public class Tracker {
    private Connection connection;

    public void setConnection(String url, String userName, String password) {
        try {
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int add(Item item) {
        int id=-99;
        try {
            PreparedStatement pst = connection.prepareStatement("insert into items(name, description, create_time) values (?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, item.getName());
            pst.setString(2, item.getDescription());
            pst.setTimestamp(3, item.getCreate());
            pst.executeUpdate();
            ResultSet genKey = pst.getGeneratedKeys();
            if (genKey.next()) {
                id=genKey.getInt(1);
            }
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean findById(int id) {
        boolean flag = false;
        try {
            String request = "select * from items where id = ";
            Statement st = connection.createStatement();
            ResultSet rst = st.executeQuery(request + String.valueOf(id));
            int columnCount = rst.getMetaData().getColumnCount();
            while (rst.next()) {
                flag = true;
                System.out.println(String.format("Find next items with id = %s. ---> Найдены следующие заявки с id = %s", id, id));
                for (int j = 1; j <= columnCount; j++) {
                    System.out.print(rst.getString(j));
                }
                System.out.println();
            }
            rst.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public void findAll() {
        Statement st = null;
        try {
            st = connection.createStatement();
            ResultSet rst = st.executeQuery("select * from items");
            int columnCount = rst.getMetaData().getColumnCount();
            while (rst.next()) {
                for (int j = 1; j <= columnCount; j++) {
                    System.out.print(rst.getString(j) + "\t");
                }
                System.out.println();
            }
            rst.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, Item item) {
        try {
            PreparedStatement pst = connection.prepareStatement("update items set name = ?, description = ? , create_time = ? where id = ?");
            pst.setString(1, item.getName());
            pst.setString(2, item.getDescription());
            pst.setTimestamp(3, item.getCreate());
            pst.setInt(4, id);
            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement pst = connection.prepareStatement("delete from items where id = ?");
            pst.setInt(1, id);
            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void findByName(String name) {
        try {
            int record = 0;
            String request = "select * from items where name = '" + name + "' ";
            String requestCount = "select count(*) from items where name = '" + name + "' ";
            Statement statement = connection.createStatement();
            ResultSet rst = statement.executeQuery(requestCount);
            while (rst.next()) {
                record += Integer.parseInt(rst.getString(1));
            }
            if (record > 0) {
                System.out.println(String.format("Number records is %s.  ---> Число найденых записей %s", record, record));
            } else {
                System.out.println("Not found. ---> Не найдено.");
            }

            rst = statement.executeQuery(request);
            int columnCount = rst.getMetaData().getColumnCount();

            while (rst.next()) {
                for (int j = 1; j <= columnCount; j++) {
                    System.out.print(rst.getString(j) + "\t");
                }
                System.out.println();
            }
            rst.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}