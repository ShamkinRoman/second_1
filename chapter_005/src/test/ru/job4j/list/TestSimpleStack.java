package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Тест Простого Стек.
 */
public class TestSimpleStack {

    /**
     * Создаем проверяемый объект.
     */
    private SimpleStack simpleStack;

    /**
     * Готовимся к тесту.
     */
    @Before
    public void prepereTest() {
        this.simpleStack = new SimpleStack();
    }

    /**
     * Тест №1.
     * Проверка метода push и размера стека.
     */
    @Test
    public void pushFourElementAndSizeEqualFour() throws Exception {
        simpleStack.push(1);
        simpleStack.push(2);
        simpleStack.push(3);
        simpleStack.push(4);

        assertThat(simpleStack.size(), is(4));
    }

    /**
     * Тест №2.
     * Проверка метода poll.
     */
    @Test
    public void pollElemetEqualLastElement() throws Exception {
        simpleStack.push(2);
        simpleStack.push(55);

        assertThat(simpleStack.poll(), is(55));
    }

}