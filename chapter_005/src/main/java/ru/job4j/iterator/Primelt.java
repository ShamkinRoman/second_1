package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Создать итератор возвращающий только простые числа.
 * (числа которые деляться только на 1 и на себя).
 */
public class Primelt implements Iterator {
    /**
     * Массив для хранения простых чисел.
     */
    private int[] simple;

    /**
     * Счетчик итераций для метода hasNext().
     */
    private int position = 0;

    /**
     * Конструктор класса.
     *
     * @param nums входящий массив.
     */

    public Primelt(int[] nums) {

        this.simple = sortNum(nums);

    }

    /**
     * Метод hasNext().
     *
     * @return true если есть элементы впереди каретки, false если нет.
     */
    @Override
    public boolean hasNext() {
        return position < this.simple.length ? true : false;
    }

    /**
     * Переписанный метод next(). Возвращает следующий элемент.
     *
     * @return возвращаемый элемент массива.
     */
    @Override
    public Object next() {

        return this.simple[position++];
    }


    /**
     * Метод для выборки простых чисел из массива.
     *
     * @param number входящий массив.
     * @return массив с простыми числами.
     */

    public int[] sortNum(int[] number) {

        List<Integer> list = new ArrayList<>();

        //Масси с простыми делителями.
        int[] num = {2, 3, 5, 7};

        int matches = 0;

        for (int value : number) {
            matches = 0;

            for (int delet : num) {
                if (value % delet == 0 & value != delet) {
                    matches++;

                }
            }

            if (matches == 0) {
                list.add(value);
            }
        }


        int[] result = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }
}