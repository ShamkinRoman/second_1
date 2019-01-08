package crudServlet;

import java.util.Map;

public class Test {
    public static void main(String[] args) {
        DBStore dbStore = new DBStore().getINSTANCE();

        User user1 = new User(dbStore.giveId(), "User44", "User44", "User44");
//        User user1 = new User(new Integer(3), "nnnUser3", "nnnUser3", "nnnUser3");
        dbStore.add(user1);

//        dbStore.delete(user1);



        for (User user : dbStore.findAllInMap().values()) {
            System.out.println(user);
        }

        System.out.println(dbStore.giveId());
        System.out.println("End program");
    }
}