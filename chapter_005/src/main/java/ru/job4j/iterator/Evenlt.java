package ru.job4j.iterator;

/**
 * Created by Up on 21.08.2017.
 */


import java.util.Iterator;


/**
 * Создать итератор возвращающий только четные цифры.
 * Итератор должен принимать список произвольных чисел.
 */
public class Evenlt implements Iterator {
    /**
     * массив для анализа.
     */
    private int[] events;

    /**
     * Номер позиции в массиве.
     */
    private int position = 0;

    /**
     * Конструктор принимающий массив чисел и производит определения количества четных чисел.
     *
     * @param numbers входящий массив.
     */
    public Evenlt(final int[] numbers) {

        this.events = numbers;

    }

    /**
     * Метод hasNext().
     * Сравнивает количесво выборок с общим количеством четных чисел.
     *
     * @return true если есть элементы впереди каретки, false если нет.
     */


    @Override
    public boolean hasNext() {

        return check();

    }

    /**
     * Переписанный метод next(). Возвращает следующий четный элемент.
     *
     * @return возвращаемый элемент массива.
     */
    @Override
    public Object next() {

        return events[position++];
    }


    /**
     * Метод лля проверки четного числа в массиве.
     *
     * @return позиция четного числа в массиве.
     */
    public boolean check() {

        boolean result = false;

        for (; position < this.events.length; position++) {
            if (events[position] % 2 == 0) {
                result = true;
                break;
            }
        }
        return result;
    }
}
