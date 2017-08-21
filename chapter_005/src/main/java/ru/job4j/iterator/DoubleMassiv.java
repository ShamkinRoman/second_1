package ru.job4j.iterator;

import java.util.Iterator;

/**
 * Created by Up on 08.08.2017.
 */
public class DoubleMassiv implements Iterator {

    /**
     * Массив для итератора, принимающий значения входящего массива.
     */
    private final int[][] value;
    /**
     * Счетчик итераций, значение -1, для реализации первой итерации метода next().
     */
    private int position = -1;

    /**
     * Конструктор класса.
     *
     * @param check входящий массив.
     */
    public DoubleMassiv(final int[][] check) {
        this.value = check;
    }


    /**
     * Переписанный метод для двумерного массива.
     *
     * @return true если есть элементы впереди каретки, false если нет.
     */
    @Override
    public boolean hasNext() {

        // Отнимаем 2, для приведения длины массива (двухмерный) и счет идет от 0.
        return position <= (value[0].length + value.length - 2) ? true : false;
    }

    /**
     * Переписанный метод next(). Возвращает следующий элемент.
     *
     * @return возвращаемый элемент массива.
     */
    @Override
    public Object next() {

        position++;

        return value[position / value[0].length][position % value[0].length];
    }

}
