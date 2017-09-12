package ru.job4j.iterator;

import java.util.Iterator;

/**
 * Created by Up on 08.08.2017.
 */
public class DoubleMassiv implements Iterator {

    /**
     * Массив для итератора, принимающий значения входящего массива.
     */
    private int[][] value;

    /**
     * значение строки массива.
     */
    private int row = 0;
    /**
     * значение столбца массива.
     */
    private int column = -1;
    /**
     * число элементов в массиве.
     */
    private int arrayLong;

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
        return value.length - 1 > row || value[row].length - 1 > column;
    }

    /**
     * Переписанный метод next(). Возвращает следующий элемент.
     *
     * @return возвращаемый элемент массива.
     */
    @Override
    public Object next() {

        if (this.column >= this.value[this.row].length - 1) {
            this.row++;
            this.column = 0;
        } else if (row < value.length) {
            this.column++;
        }

        return this.value[this.row][this.column];
    }
}