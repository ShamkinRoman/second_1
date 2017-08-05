package ru.job4j.newtracker;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Up on 05.08.2017.
 */
public class TestStartUIBase {
    /**
     * Тест 1 для метода Найти заявку.
     */
    @Test
    public void whenFindByNameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("Vova", "Smirnov", 1);
        tracker.add(item);
        assertThat(tracker.findByName("Vova").get(0), is(item));
    }

    /**
     * Тест 2 для проверки метода Добавить заявку.
     */
    @Test

    public void addItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("Vova", "Smirnov", 1);
        tracker.add(item);
        assertThat(tracker.findAll().get(0), is(item));
    }

    /**
     * Тест 3 для метода Поис заявки по Id.
     */
    @Test
    public void whenfindById() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        Item item1 = new Item("anotherTest2", "anotherDescription", 2123L);
        tracker.add(item);
        tracker.add(item1);
        assertThat(tracker.findById(item.getId()), is(item));
    }

    /**
     * Тест 4 для проверки метода Удаление заявки.
     */
    @Test
    public void whenDeleteItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        Item item1 = new Item("anotherTest2", "anotherDescription", 2123L);
        tracker.add(item);
        tracker.add(item1);
        tracker.delete(item);
        List<Item> list = tracker.findAll();
        if (!list.contains(item)) {
            assertThat(1, is(1));
        } else {
            assertThat(0, is(1));
        }

    }

    /**
     * Тест 5 для проверки метода Найти Все заявки.
     */
    @Test
    public void whenFindAll() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        Item item1 = new Item("anotherTest2", "anotherDescription", 2123L);
        tracker.add(item);
        tracker.add(item1);
        List<Item> list = new ArrayList<>();
        list.add(item);
        list.add(item1);

        if (tracker.findAll().containsAll(list) && list.containsAll(tracker.findAll())) {
            assertThat(1, is(1));
        } else {
            assertThat(0, is(1));
        }

    }

    /**
     * Тест 6 для проверки метода Редактирования заявки.
     * <p>
     * Не заню как его реализовать (((
     */

    @Test
    public void whenUpdateItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        Item item1 = new Item("anotherTest2", "anotherDescription", 2123L);
        tracker.update(item);
        assertThat(tracker.findAll().get(0), is(item));
    }

}
