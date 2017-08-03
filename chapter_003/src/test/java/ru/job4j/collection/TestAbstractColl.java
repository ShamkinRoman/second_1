package ru.job4j.collection;

import org.junit.Test;

/**
 * Определение времени на создание и удаление из коллекции.
 */
public class TestAbstractColl {
    /**
     * Тестирование коллекций.
     */
    @Test
    public void whenArray() {
        ListMassiv test = new ListMassiv();
        /** amount количество элементов.*/
        int amount = 100000;
        test.arrList(amount);
        test.linkList(amount);
        test.woodList(amount);
    }
}