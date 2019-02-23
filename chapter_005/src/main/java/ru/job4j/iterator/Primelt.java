package ru.job4j.iterator;


import    java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Создать итератор возвращающий только простые числа.
 * (числа которые деляться только на 1 и на себя).
 */
public class Primelt implements Iterator {
    /**
     * Массив для анализа простых чисел.
     */
    private int[] simple;

    /**
     * Номер позиции в анализируемом массиве.
     */
    private int position = 0;

    /**
     * Конструктор класса.
     *
     * @param nums входящий массив.
     */

    public Primelt(int[] nums) {

        this.simple = nums;

    }

    /**
     * Метод hasNext().
     *
     * @return true если есть элементы впереди каретки, false если нет.
     */
    @Override
    public boolean hasNext() {
        return check();
    }

    /**
     * Переписанный метод next(). Возвращает следующий элемент.
     *
     * @return возвращаемый элемент массива.
     */
    @Override
    public Object next() {

        return check()  ? this.simple[position++] : new NoSuchElementException();

    }

    /**
     * Метод для определения простых чисел.
     *
     * @return позиция простого числа в массиве.
     */

    public boolean check() {

        //Массив с элементарными числами.
        int[] prime = {2, 3, 5, 7};
        int match = 0;
        boolean result = false;

        while (position < simple.length) {
            match = 0;
            for (int value : prime) {
                if (simple[position] % value == 0 & simple[position] != value) {
                    match++;
                }
            }

            if (match == 0) {
                result = true;
                break;
            }
            position++;
        }

        return result;
    }
}