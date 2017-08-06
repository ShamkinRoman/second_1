package ru.job4j.colsorted;


import org.junit.Test;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * .Тест для проверки метода sort класса SortUser..
 */
public class TestSortUser {
    /**
     * Реализация теста.
     */
    @Test
    public void whenSortedByAge() {

        User user1 = new User("Яблочкин", 45);
        User user2 = new User("Абрикосов", 32);
        User user3 = new User("Мясников", 55);

        ArrayList<User> list = new ArrayList<>();

        list.add(user1);
        list.add(user2);
        list.add(user3);

        SortUser sortUser = new SortUser();

        Set<User> proverka = sortUser.sort(list);

        Iterator<User> iterator = proverka.iterator();


        assertThat(iterator.next(), is(user2));
        assertThat(iterator.next(), is(user1));
        assertThat(iterator.next(), is(user3));

    }
}