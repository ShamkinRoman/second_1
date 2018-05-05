package httpServelet;

import java.sql.Timestamp;

public class TestConnect {
    public static void main(String[] args) {
        CheckConnectToDataBase s1 = new CheckConnectToDataBase();
        s1.init();
        MemoryStore mr = new MemoryStore(s1.getProperties());
        User u1 = new User("Q", "w","ssd",new Timestamp(System.currentTimeMillis()));
        mr.add(u1);
    }
}
