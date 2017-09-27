package ru.job4j.list;
//Я вернулся...
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Тестируем Связанный список.
 */
public class MyLinkedListTest {

    /**
     * Создаем проверяемый объект.
     */
    private MyLinkedList myLinkedList = new MyLinkedList();

    /**
     * Готовимся к тесту.
     */
    @Before
    public void prepareTest() {

        myLinkedList.add(0);
        myLinkedList.add(1);
        myLinkedList.add(2);

    }

    /**
     * Тест №1.
     * Проверка метода GET.
     */
    @Test
    public void whenCheckGet() {

        assertThat(myLinkedList.get(0), is(0));
        assertThat(myLinkedList.get(1), is(1));
        assertThat(myLinkedList.get(0), is(0));
        assertThat(myLinkedList.get(2), is(2));

    }

    /**
     * Тест №2.
     * Проверка метода size.
     */
    @Test
    public void whenCheckSize() {

        int expectedSize = 3;

        assertThat(myLinkedList.size(), is(expectedSize));

    }

    /**
     * Тест №3.
     * Проверка метода iterator.
     */
    @Test
    public void whenCheckIterator() {

        Iterator it = myLinkedList.iterator();

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(0));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));

        assertThat(it.hasNext(), is(false));


    }

    /**
     * Проверка метода iterator пустого списка и когда отсутствует элемент.
     */
    @Test
    public void whenEmptyListThenNoIteratorAndNotGetValue() {

        MyLinkedList list = new MyLinkedList();

        Iterator itr = list.iterator();

        try {
            itr.next();
        } catch (NoSuchElementException nsee) {
            assertThat(nsee.getMessage(), is("Не найдено"));
        }

        try {
            list.get(1);
        } catch (IndexOutOfBoundsException ioobe) {
            assertThat(ioobe.getMessage(), is("нет элементов в заданном диапазоне"));
        }
    }
}