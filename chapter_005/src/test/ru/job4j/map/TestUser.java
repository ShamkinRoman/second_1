package ru.job4j.map;

import org.junit.Test;


import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Проверяем коллекцию МАР по бизнес логике, с переписанными методами HASH and EQUAL.
 */
public class TestUser {

    /**
     * Тест1.
     * Методы HASH and EQUAL не переписанны.
     */
    @Test
    public void whenNotOverride() {

        User user1 = new User("Roman", 2, new GregorianCalendar(1988, 12, 12));
        User user2 = new User("Roman", 2, new GregorianCalendar(1988, 12, 12));
        User user3 = new User("Roman", 2, new GregorianCalendar(1988, 12, 12));

        Map<User, Object> testMap = new HashMap<>();

        testMap.put(user1, 1);
        testMap.put(user2, 2);
        testMap.put(user3, 3);

        assertThat(testMap.size(), is(3));
        assertThat(testMap.get(user2), is(2));
    }

    /**
     * Тест2.
     * Переписан только один метод HASH.
     */
    @Test
    public void whenOverrideHash() {

        HashUser user1 = new HashUser("Roman", 2, new GregorianCalendar(1988, 12, 12));
        HashUser user2 = new HashUser("Roman", 2, new GregorianCalendar(1988, 12, 12));
        HashUser user3 = new HashUser("Roman", 2, new GregorianCalendar(1988, 12, 12));

        Map<HashUser, Object> testMap = new HashMap<>();

        testMap.put(user1, 1);
        testMap.put(user2, 2);
        testMap.put(user3, 3);

        assertThat(testMap.size(), is(3));
        assertThat(testMap.get(user2), is(2));

    }

    /**
     * Тест3.
     * Переписан только один метод EQUAL.
     */
    @Test
    public void whenOverrideEqual() {

        EqualUser user1 = new EqualUser("Roman", 2, new GregorianCalendar(1988, 12, 12));
        EqualUser user2 = new EqualUser("Roman", 2, new GregorianCalendar(1988, 12, 12));
        EqualUser user3 = new EqualUser("Roman", 2, new GregorianCalendar(1988, 12, 12));

        Map<EqualUser, Object> testMap = new HashMap<>();

        testMap.put(user1, 1);
        testMap.put(user2, 2);
        testMap.put(user3, 3);

        assertThat(testMap.size(), is(3));
        assertThat(testMap.get(user2), is(2));
    }

    /**
     * Тест4.
     * Переписаны методы HASH and EQUAL.
     */
    @Test
    public void whenOverradeHashEqual() {
        EqualHashUser user1 = new EqualHashUser("Roman", 2, new GregorianCalendar(1988, 12, 12));
        EqualHashUser user2 = new EqualHashUser("Roman", 2, new GregorianCalendar(1988, 12, 12));
        EqualHashUser user3 = new EqualHashUser("Roman", 2, new GregorianCalendar(1988, 12, 12));

        Map<EqualHashUser, Object> testMap = new HashMap<>();

        testMap.put(user1, 1);
        testMap.put(user2, 2);
        testMap.put(user3, 3);

        assertThat(testMap.size(), is(1));
        assertThat(testMap.get(user2), is(3));

    }


}
