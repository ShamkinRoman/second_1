package ru.job4j.colsorted;


import org.junit.Test;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * .Тест для проверки методов класса SortUser..
 */
public class TestSortUser {
    /**
     * Тест 1.
     * Реализация теста.
     *
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

    /**
     * Тест 2.
     * Реализация теста для метода sortNameLength.
     */
    @Test
    public void whenSortedNameLength() {
        User user1 = new User("Ябл", 45);
        User user2 = new User("Абрикосов", 32);
        User user3 = new User("Мясников", 55);

        ArrayList<User> list = new ArrayList<>();

        list.add(user1);
        list.add(user2);
        list.add(user3);

        SortUser sortUserNameLength = new SortUser();

        List<User> proverka = sortUserNameLength.sortNameLength(list);

        assertThat(proverka.get(0), is(user1));
        assertThat(proverka.get(1), is(user3));
        assertThat(proverka.get(2), is(user2));


    }

    /**
     * Тест 3.
     * Реализация теста для метода sortByAllFields
     */
    @Test
    public void whenSortedByAllFields() {
        User user1 = new User("Абрикосов", 82);
        User user2 = new User("Ябло", 45);
        User user3 = new User("Ябл", 45);
        User user4 = new User("Абрикосов", 32);
        User user5 = new User("Мясников", 55);
        User user6 = new User("Ябл", 15);
        User user7 = new User("Мяснико", 55);

        ArrayList<User> list = new ArrayList<>();

        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        list.add(user6);
        list.add(user7);

        SortUser sortUserNameLength = new SortUser();

        List<User> proverka = sortUserNameLength.sortByAllFiedlds(list);

        assertThat(proverka.get(0), is(user6));
        assertThat(proverka.get(1), is(user3));
        assertThat(proverka.get(2), is(user2));
        assertThat(proverka.get(3), is(user7));
        assertThat(proverka.get(4), is(user5));
        assertThat(proverka.get(5), is(user4));
        assertThat(proverka.get(6), is(user1));

    }

}