package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Тест для определения цикличности.
 */
public class TestCyclicList {

    /**
     * Тест №1.
     * Когда есть цикличность.
     *
     * @throws Exception ошибка.
     */
    @Test
    public void hasCycle() throws Exception {

        CyclicList cyclicList = new CyclicList();

        Node first = new Node(1);
        Node two = new Node(2);
        Node third = new Node(3);
        Node four = new Node(4);


        first.setNext(two);
        two.setNext(third);
        third.setNext(four);
        four.setNext(first);

        assertThat(cyclicList.hasCycle(first), is(true));

    }

    /**
     * Тест №2.
     * когда нет цикличности.
     */

    @Test
    public void cycles2() {

        CyclicList cyclicList = new CyclicList();

        Node first = new Node(1);
        Node two = new Node(2);
        Node third = new Node(3);
        Node four = new Node(4);

        first.setNext(two);
        two.setNext(four);

        assertThat(cyclicList.hasCycle(first), is(false));
    }

}