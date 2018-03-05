package sqlTracker;

import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUIBaseTest {
    private String url = "jdbc:postgresql://localhost:5432/";
    private String userName = "postgres";
    private String password = "Uht,tymrjdf42";
    private String databaseName = "ddzz";
    private Tracker tracker = new Tracker();
    Timestamp timestamp;

    @Before
    public void prepareTest() {
        tracker.setConnection(url + databaseName, userName, password);
        timestamp = new Timestamp(System.currentTimeMillis());
    }

    @Test
    public void whenAddItemItemsIncrement() {
        Item item = new Item("test", "descTest", timestamp);
        int testingValue = 1;
        int actualValue = -99;
        int increment = -999;
        try {
            Statement statement = tracker.getConnection().createStatement();
            ResultSet rst = statement.executeQuery("select count(*) from items");
            while (rst.next()) {
                actualValue = rst.getInt(1);
            }
            tracker.add(item);
            rst = statement.executeQuery("select count(*) from items");
            while (rst.next()) {
                increment = rst.getInt(1) - actualValue;
            }
            rst.close();
            statement.close();
            assertThat(testingValue, is(increment));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenUpdateItem() {
        String name = String.valueOf(System.currentTimeMillis());
        String desc = String.valueOf(System.currentTimeMillis());
        Timestamp time = timestamp;
        Item item = new Item(name, desc, time);
        int id = tracker.add(item);
        String updName = "updatename";
        String updDesc = "updateDescr";
        Timestamp updTime = timestamp;
        Item updateItem = new Item(updName, updDesc, updTime);
        String testName = null;
        String testDesc = null;
        Timestamp testTime = null;

        try {
            Statement statement = tracker.getConnection().createStatement();
            ResultSet rst = statement.executeQuery("select * from items where id = " + String.valueOf(id));
            while (rst.next()) {
                testName = rst.getString(2);
                testDesc = rst.getString(3);
                testTime = rst.getTimestamp(4);
            }

            assertThat(testName, is(name));
            assertThat(testDesc, is(desc));
            assertThat(testTime, is(time));

            tracker.update(id, updateItem);

            rst = statement.executeQuery("select * from items where id = " + String.valueOf(id));
            while (rst.next()) {
                testName = rst.getString(2);
                testDesc = rst.getString(3);
                testTime = rst.getTimestamp(4);
            }

            assertThat(testName, is(updName));
            assertThat(testDesc, is(updDesc));
            assertThat(testTime, is(updTime));

            rst.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenDeleteItem() {
        String name = "Delete";
        String desc = "DeleteDesc";
        int id = tracker.add(new Item(name, desc, timestamp));
        int testCountBefore = 1;
        int testCountAfter = 0;
        int actualCount = -99;
        try {
            Statement statement = tracker.getConnection().createStatement();
            ResultSet rst = statement.executeQuery("select count(*) from items where id = " + String.valueOf(id));
            while (rst.next()) {
                actualCount = rst.getInt(1);
            }

            assertThat(testCountBefore, is(actualCount));

            tracker.delete(id);

            rst = statement.executeQuery("select count(*) from items where id = " + String.valueOf(id));
            while (rst.next()) {
                actualCount = rst.getInt(1);
            }

            assertThat(testCountAfter, is(actualCount));

            rst.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenFindName() {
        String name = "FindName";
        String desc1 = "FindDescOne";
        String desc2 = "FindDescTwo";
        Item item1 = new Item(name, desc1, timestamp);
        Item item2 = new Item(name, desc2, timestamp);
        int count = -99;
        int testCountBefore = 0;
        int testCountAfter = 2;
        try {
            Statement statement = tracker.getConnection().createStatement();
            statement.execute("delete from items where name ='"
                    + name
                    + "'");

            ResultSet rst = statement.executeQuery("select count(*) from items where name = '"
                    + name
                    + "'");
            while (rst.next()) {
                count = rst.getInt(1);
            }

            assertThat(testCountBefore, is(count));

            tracker.add(item1);
            tracker.add(item2);

            rst = statement.executeQuery("select count(*) from items where name = '"
                    + name
                    + "'");
            while (rst.next()) {
                count = rst.getInt(1);
            }

            assertThat(testCountAfter, is(count));

            rst.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}