package ru.job4j.storage;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStorageTest {

private UserStorage userStorage = new UserStorage();

private     User user1= new User(1,100);
private     User user2 = new User(2,200);


    @Test
    public void add() throws Exception {


        assertThat(userStorage.add(user1), is (true));
        assertThat(userStorage.add(user2), is (true));

        assertThat(userStorage.add(user1), is (false));
        assertThat(userStorage.add(user2), is (false));

        User user3 = new User(1,120);
        assertThat(userStorage.add(user3), is (false));

    }

    @Test
    public void delete() throws Exception {

        assertThat(userStorage.add(user1), is (true));
        assertThat(userStorage.add(user2), is (true));


        User user3 = new User(3,120);

        assertThat(userStorage.delete(user1),is(true));
        assertThat(userStorage.delete(user3),is(false));
        assertThat(userStorage.delete(user2),is(true));
        assertThat(userStorage.delete(user1),is(false));


    }

    @Test
    public void update() throws Exception {

        assertThat(userStorage.add(user1), is (true));
        assertThat(userStorage.add(user2), is (true));

        User user3=new User(1,300);

        assertThat((userStorage.update(user3)), is (true));

    }

    @Test
    public void transfer() throws Exception {

        assertThat(userStorage.add(user1), is (true));
        assertThat(userStorage.add(user2), is (true));

        assertThat(userStorage.transfer(1,2,99), is (true));

        assertThat(userStorage.transfer(1,2,99), is (false));

    }

}