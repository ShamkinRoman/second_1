package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Тестирование класса LinkedSet, Множество построенного на связанном списке.
 */
public class LinkedSetTest {

    /**
     * Тест №1.
     * Добавляются только уникальные значения.
     * @throws Exception ошибка.
     */
    @Test
    public void add() throws Exception {

        LinkedSet<Integer> linkedSet = new LinkedSet();
        linkedSet.add(1);
        linkedSet.add(2);

        linkedSet.add(1);
        linkedSet.add(2);

        assertThat(linkedSet.length(), is (2)); //размер множества равен 2.

    }

    /**
     * Тест №2.
     * Проверка итератора.
     * @throws Exception
     */
    @Test
    public void iterator() throws Exception {

        LinkedSet<Integer> linkedSet = new LinkedSet();

        linkedSet.add(1);
        linkedSet.add(2);

        Iterator it = linkedSet.iterator();

        assertThat(it.hasNext(), is (true));
        assertThat(it.next(), is (1));

        assertThat(it.hasNext(), is (true));
        assertThat(it.next(), is (2));

        assertThat(it.hasNext(), is (false));

        try {
            it.next();
        } catch (NoSuchElementException nsee) {
            assertThat(nsee.getMessage(), is("Нет больше элементов."));
        }

    }

}