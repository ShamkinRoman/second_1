package ru.job4j.calculator;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Up on 18.06.2017.
 */
public class StunInputTest {
    /**
     * Тест 1.
     * Проверяем метод findAll
     * Проверяем метод findByName
     * Проверяем метод findById
     */
    @Test
    public void whenaddItem() {
        Tracker tracker = new Tracker();
        Input input = new StunInput(new String[] {"0", "Test name", "Test desc", "1", "1", "6"});
        new StartUI(tracker, input).select();
        assertThat(tracker.findAll()[0].getName(), is("Test name"));
        assertThat(tracker.findByName("Test name")[0].getName(), is("Test name"));
        assertThat(tracker.findById(tracker.findByName("Test name")[0].getId()).getName(), is("Test name"));
    }
    /**
     * Тест 2.
     * Проверяем метод update
     */
    @Test
    public void whenEditItem() {
        Tracker tracker = new Tracker();
        Input input = new StunInput(new String[] {"0", "Test1 name", "Test1 desc", "2", "1", "6"});
        new StartUI(tracker, input).select();
        String id = tracker.findAll()[0].getId();
        Input input1 = new StunInput(new String[] {"2", id, "Edit name", "Edit desc", "666", "1", "6"});
        new StartUI(tracker, input1).select();
        assertThat(tracker.findAll()[0].getName(), is("Edit name"));
    }
    /**
     * Тест 3.
     * Проверяем метод delete
     * создал item и присвоил null для сравнения
     */
    @Test
    public void whenDelete() {
        Tracker tracker = new Tracker();
        Input input = new StunInput(new String[] {"0", "Test1 name", "Test1 desc", "1", "0", "Test2 name", "Test2 desc", "2", "1", "6"});
        new StartUI(tracker, input).select();
        String id = tracker.findAll()[0].getId();
        Input input1 = new StunInput(new String[] {"3", id, "6"});
        new StartUI(tracker, input1).select();
        assertThat(tracker.findAll()[0].getName(), is("Test2 name"));
        Item item = null;
        assertThat(tracker.findById(id), is(item));
    }
}
