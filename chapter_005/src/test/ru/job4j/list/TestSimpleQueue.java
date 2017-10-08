package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Тест простой очереди.
 */
public class TestSimpleQueue {

    /**
     * Создаем проверяемый объект.
     */
    SimpleQueue simpleQueue;

    /**
     * Готовимся к тесту.
     */
    @Before
    public void prepereTest() {
        simpleQueue = new SimpleQueue();
    }

    /**
     * Тест №1.
     * Проверка метода push и размера очереди..
     */
    @Test
    public void push() throws Exception {
        simpleQueue.push(1);
        simpleQueue.push(2);
        simpleQueue.push(3);
        assertThat(simpleQueue.size(), is(3));
    }

    /**
     * Тест №2.
     * Проверка метода poll.
     */
    @Test
    public void poll() throws Exception {
        simpleQueue.push(1);
        simpleQueue.push(33);

        assertThat(simpleQueue.poll(), is(1));
    }

}