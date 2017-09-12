package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Проверка класса DynamicList.
 */
public class DynamicListTest {

    /**
     * Создаем экземпляр.
     */
    private DynamicList dynamicList = new DynamicList();

    /**
     * Готовимся к тесту.
     */
    @Before
    public void prepareTest() {

        dynamicList.add(0);
        dynamicList.add(1);
        dynamicList.add(2);

    }

    /**
     * Тест №1.
     * Проверка метода get и  add.
     *
     * @throws Exception
     */
    @Test
    public void whenCheckGetAndAdd() {


        assertThat(dynamicList.get(0), is(0));
        assertThat(dynamicList.get(1), is(1));
        assertThat(dynamicList.get(2), is(2));

        dynamicList.add(0);

        assertThat(dynamicList.get(3), is(0));

    }

    /**
     * Тест №2.
     * Проверка длины массива.
     */
    @Test
    public void whenCheckArray() {

        DynamicList userList = new DynamicList(1);

        assertThat(userList.length(), is(0));

        userList.add("Test");
        assertThat(userList.length(), is(1));

        userList.add("More Test");
        assertThat(userList.length(), is(2));

        userList.add("More more Test");
        assertThat(userList.length(), is(3));

    }

    /**
     * Тест №3.
     * проверка итератора.
     */
    @Test
    public void whenCheckIterator() throws Exception {

        Iterator itr = dynamicList.iterator();

        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is(0));

        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is(1));

        assertThat(itr.hasNext(), is(true));
        assertThat(itr.next(), is(2));

        assertThat(itr.hasNext(), is(false));

        try {
            itr.next();
        } catch (IndexOutOfBoundsException ioobe) {
            assertThat(ioobe.getMessage(), is("Вышли за диапазон массива"));
        }

    }

}