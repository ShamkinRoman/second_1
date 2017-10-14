package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Тест множества реализованного на массиве.
 */
public class ArraySetTest {

    /**
     * Тест №1.
     * проверка метода add.
     * @throws Exception ошибка.
     */
    @Test
    public void add() throws Exception {

        ArraySet arraySet = new ArraySet();

        arraySet.add(1);
        arraySet.add(2);
        arraySet.add(3);

        assertThat(arraySet.lengthSet(), is(3));

        arraySet.add(1);
        arraySet.add(2);
        arraySet.add(3);

        assertThat(arraySet.lengthSet(), is(3));

    }

    /**
     * Тест №2.
     * Проверка итератора.
     * @throws Exception ошибка.
     */
    @Test
    public void iterator() throws Exception {

        ArraySet arraySet = new ArraySet();

        arraySet.add(1);
        arraySet.add(2);
        arraySet.add(3);

        Iterator it = arraySet.iterator();

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));

        assertThat(it.hasNext(), is(false));

        try {
            it.next();
        } catch (NoSuchElementException nsee) {
            assertThat(nsee.getMessage(), is("Нет больше элементов."));
        }

    }

}