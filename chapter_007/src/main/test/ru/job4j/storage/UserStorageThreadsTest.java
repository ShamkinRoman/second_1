package ru.job4j.storage;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserStorageThreadsTest {


    private class Add implements Runnable {

        UserStorage userStorage;

        public Add(UserStorage userStorage) {
            this.userStorage = userStorage;
        }

        public ArrayList<User> prepareAdd() {
            List<User> listUser = new ArrayList<>();
            for (int i = 1; i < 580; i++) {
                listUser.add(new User(i, i * 100));

            }

            return (ArrayList<User>) listUser;
        }

        public void add () {

            Random random = new Random();
            ArrayList<User> userArrayList =prepareAdd();

            int i=0;
            while(i++<14000) {

                userStorage.add(userArrayList.get(random.nextInt(578)));
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }

        @Override
        public void run() {

            add();

        }
    }


    private UserStorage userStorage = new UserStorage();


    @Test
    public void threadStart() {

    Thread one = new Thread(new Add(userStorage));

    one.start();
        try {
            one.join(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(userStorage.size());

    }

}
