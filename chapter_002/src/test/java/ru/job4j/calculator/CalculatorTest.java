package ru.job4j.calculator;
import org.junit.Test;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 *Test Calculator.
 *@author Shamkin Roman.
 *@since 28.05.2017.
 */
public class CalculatorTest {
    /**
     *Тест 1.
     *поис по имени
     */
    @Test
    public void whenFindByNameThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findByName("test1")[0], is(item));
    }
    /**
     *Тест 2.
     *добавление item
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        assertThat(tracker.add(item), is(item));
    }
    /**
     *Тест 3.
     *поиск по id.
     */
    @Test
    public void whenFindByIdThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        Item item1 = new Item("test2", "testDescription", 123L);
        tracker.add(item);
        tracker.add(item1);
        assertThat(tracker.findById(item.getId()), is(item));
    }
    /**
     *Тест 4.
     *удаление item.
     *в результате поиска объект не найден, т.е. null (удален)
     */
    @Test
    public void whenDeleteThenTrackerHasNullItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        Item item1 = new Item("test2", "testDescription", 123L);
        tracker.add(item);
        tracker.add(item1);
        tracker.delete(item);
        Item item2 = new Item();
        item2 = null;
        assertThat(tracker.findByName("test1"), is(item2));
    }
    /**
     *Тест 5.
     *найти все item которые не равны null
     */
    @Test
    public void whenFindAllThenTrackerHasSeveralItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        Item item1 = new Item("test2", "testDescription", 123L);
        tracker.add(item);
        tracker.add(item1);
        Item[] result = new Item[2];
        result[0] = item;
        result[1] = item1;
        assertThat(tracker.findAll(), is(result));
    }

}
