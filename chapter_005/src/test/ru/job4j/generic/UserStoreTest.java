package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Тест для проверки класса UserStore
 */
public class UserStoreTest {
    /**
     * userStore для теста.
     */
    private UserStore userStore = new UserStore();
    /**
     * user1 для теста.
     */
    private User user1 = new User();
    /**
     * user2 для теста.
     */
    private User user2 = new User();

    /**
     * Подготовка к тестам.
     * Начальные значения перед тестами.
     */
    @Before
    public void prepereTest() {

        user1.setId("11");
        user2.setId("22");

        userStore.add(user1);
        userStore.add(user2);
    }

    /**
     * Тест №1. Проверка метода Add и Delete.
     */
    @Test
    public void whenAddAndDelete() {

        assertThat(userStore.get("11"), is(user1));
        assertThat(userStore.get("22"), is(user2));

        userStore.delete("22");

        try {
            userStore.get("22");
        } catch (NoSuchElementException nsee) {
            assertThat(nsee.getMessage(), is("Не найдено"));
        }

    }

    /**
     * Тест №2. Проверка метода UPDATE.
     */
    @Test
    public void whenUpdate() {

        User user3 = new User();

        assertThat(userStore.get("11"), is(user1));
        userStore.update("11", user3);

        assertThat(userStore.get("11"), is(user3));

    }

}