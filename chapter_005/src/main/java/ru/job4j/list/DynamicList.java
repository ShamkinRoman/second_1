package ru.job4j.list;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Создание динамического массива.
 *
 * @param <E> дженерик.
 */

public class DynamicList<E> implements SimpleContainer<E> {
    /**
     * Размер массива по умолчанию.
     */

    private final int defaultSize = 10;

    /**
     * Счетчик занятых ячеек в массиве.
     */
    private int position = 0;

    /**
     * Динамический массив.
     */
    private Object[] dataStore;

    /**
     * Конструктор массива с размером по умолчанию.
     */
    public DynamicList() {
        this.dataStore = new Object[defaultSize];
    }

    /**
     * Конструктор массива с размером указанным пользователем.
     *
     * @param userSize размер массива.
     */
    public DynamicList(int userSize) {
        this.dataStore = new Object[userSize];
    }

    /**
     * Метод для получения значения по номеру ячейки массива.
     *
     * @param index номер ячейки массива.
     * @return Значение элемента.
     */
    public E get(int index) {
        return (E) dataStore[index];
    }

    /**
     * Метод для добавления значения в массив.
     *
     * @param e значение добавляемого элемента.
     */
    public void add(E e) {
        checkArray(position + 1);
        dataStore[position++] = e;
    }

    /**
     * Метод для проверки размера массива.
     * Если при добавлении элемента, размер массива выходит за размеры массива, то размер массива увеличивается.
     *
     * @param checkArrayLength размер проверяемого массива.
     */
    public void checkArray(int checkArrayLength) {

        if (checkArrayLength > dataStore.length) {
            int multi = 2;
            dataStore = Arrays.copyOf(dataStore, position * multi);
        }
    }

    /**
     * Длина массива (число занятых ячеек в массиве).
     *
     * @return длина массива.
     */
    public int length() {
        return position;
    }

    /**
     * Метод итератор.
     *
     * @return итератор.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iter();
    }

    /**
     * Класс Итератор для Димнамического Массива.
     */
    private class Iter implements Iterator<E> {
        /**
         * Число элементов.
         */
        private int caretka = 0;

        /**
         * Метод hasNext.
         *
         * @return true если есть впреди каретки элементы, false если нет.
         */
        @Override
        public boolean hasNext() {
            return caretka < position;
        }

        /**
         * Метод next.
         *
         * @return элемент и переводит каретку на следующий элемент.
         */
        @Override
        public E next() {

            E result = null;

            if (hasNext()) {

                result = get(caretka++);

            } else {
                throw new IndexOutOfBoundsException("Вышли за диапазон массива");
            }

            return result;
        }
    }
}