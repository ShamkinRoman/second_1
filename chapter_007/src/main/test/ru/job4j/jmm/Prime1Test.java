package ru.job4j.jmm;

import org.junit.Test;

/**
 * Тестируем многопоточность при одновременном доступе к одной переменной.
 */

public class Prime1Test {

    /**
     * Тест №1.
     *
     * @throws Exception ошибка.
     */
    @Test
    public void inrcement() throws Exception {

        int limit = 100_000_000; //Задаем лимит на инкремент, т.е. от 0 до limit .

        User user = new User(0); // один класс для двух потоков.

        Thread one = new Thread(new Prime1(user, limit));
        Thread two = new Thread(new Prime1(user, limit));

        one.start();
        two.start();

        one.join();
        two.join();

        System.out.println(String.format("Из %s было получено %s, потеря составляет %s", limit * 2, user.getNumber(), limit * 2 - user.getNumber()));


    }

}