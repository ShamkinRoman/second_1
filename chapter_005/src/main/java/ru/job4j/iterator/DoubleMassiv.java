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
     * Счетчик итераций, значение -1, для реализации первой итерации метода next().
     */
    private int position = 0;
    /**
     * значение строки массива.
     */
    private int x = 0;
    /**
     * значение столбца массива.
     */
    private int y = -1;
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
        this.arrayLong = arrayLength(check);
    }


    /**
     * Переписанный метод для двумерного массива.
     *
     * @return true если есть элементы впереди каретки, false если нет.
     */
    @Override
    public boolean hasNext() {

        // Отнимаем 2, для приведения длины массива (двухмерный) и счет идет от 0.


        return this.position <= this.arrayLong ? true : false;
    }

    /**
     * Переписанный метод next(). Возвращает следующий элемент.
     *
     * @return возвращаемый элемент массива.
     */
    @Override
    public Object next() {
        if (this.y >= this.value[this.x].length - 1) {
            this.x++;
            this.y = 0;
        } else {
            this.y++;
        }
        this.position++;
        return this.value[this.x][this.y];
    }

    /**
     * Метод для определения количества элементов в массиве.
     * @param array входящий массив.
     * @return количество элементов в массиве.
     */

    public int arrayLength(int[][] array) {

        int lenghtArray = 0;

        for (int[] arrayValue : array) {

            lenghtArray += arrayValue.length - 1;
        }
        return lenghtArray;
    }

}
