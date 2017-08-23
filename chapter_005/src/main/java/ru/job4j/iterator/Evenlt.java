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
     * Общее число четных элементов в массиве.
     */
    private int count = 0;

    /**
     * количество выбранных четных элементов.
     */
    private int iteration = 0;

    /**
     * Конструктор принимающий массив чисел и производит определения количества четных чисел.
     *
     * @param numbers входящий массив.
     */
    public Evenlt(final int[] numbers) {

        this.events = numbers;
        this.count = lengthEven(numbers);

    }

    /**
     * Метод hasNext().
     * Сравнивает количесво выборок с общим количеством четных чисел.
     *
     * @return true если есть элементы впереди каретки, false если нет.
     */


    @Override
    public boolean hasNext() {
        return this.iteration < this.count ? true : false;

    }

    /**
     * Переписанный метод next(). Возвращает следующий четный элемент.
     *
     * @return возвращаемый элемент массива.
     */
    @Override
    public Object next() {

        int result = -999;

        for (; this.position < this.events.length; position++) {

            if (this.events[this.position] % 2 == 0) {
                result = this.events[this.position];
                this.iteration++; //номер от общего числа четных чисел.
                this.position++;  //увеличение позиции в массиве.
                break;
            }
        }
        return result;
    }

    /**
     * Метод для определения количества четных чисел из входящего массива.
     *
     * @param numbers входящий массив.
     * @return количество четных чисел в массиве.
     */

    public int lengthEven(int[] numbers) {
        int count = 0;
        for (int value : numbers) {
            if (value % 2 == 0) {
                count++;
            }
        }
        return count;
    }
}
