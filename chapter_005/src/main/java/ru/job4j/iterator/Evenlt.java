package ru.job4j.iterator;

/**
 * Created by Up on 21.08.2017.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Создать итератор возвращающий только четные цифры.
 * Итератор должен принимать список произвольных чисел.
 */
public class Evenlt implements Iterator {
    /**
     *
     */
    //private int[] events;

    /**
     * Массив для четных чисел.
     */
    private int[] sort;
    /**
     * Счетчик итераций для метода hasNext().
     */
    private int position = 0;

    /**
     * Конструктор принимающий массив чиссл и производит выборку четных чисел в новый массив.
     *
     * @param numbers входящий массив.
     */
    public Evenlt(final int[] numbers) {
        //this.events = numbers;
        this.sort = sortEven(numbers);
    }

    /**
     * Метод hasNext().
     *
     * @return true если есть элементы впереди каретки, false если нет.
     */


    @Override
    public boolean hasNext() {
        return position < this.sort.length ? true : false;

    }

    /**
     * Переписанный метод next(). Возвращает следующий элемент.
     *
     * @return возвращаемый элемент массива.
     */
    @Override
    public Object next() {
        return this.sort[position++];

    }

    /**
     * Метод для выборки четных чисел из входящего массива.
     *
     * @param number входящий массив.
     * @return массив с четными числами.
     */
    public int[] sortEven(int[] number) {

        List<Integer> sorted = new ArrayList<>();

        for (int value : number) {
            if (value % 2 == 0) {
                sorted.add(value);
            }
        }

        //возвращаемый массив с четными числами.
        int[] evenArray = new int[sorted.size()];

        for (int i = 0; i < sorted.size(); i++) {
            evenArray[i] = sorted.get(i);
        }
        return evenArray;
    }
}
