package ru.job4j.set;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Реализовать коллекцию Set на массиве.
 * Реализовать коллекцию SimpleSet. Коллекция должна обеспечивать void add(E e) и реализовывать Iterator<E>.
 * Коллекция не должна хранить дубликаты.
 * Set - внутри для хранения данных использует обычные массивы.
 *
 * @param <E> дженерик.
 */
public class ArraySet<E> implements Iterable<E> {


    /**
     * Размер массива для записи данных.
     */
    private int size;
    /**
     * Размер массива для записи данных по умолчанию.
     */
    private int defaultSize = 10;
    /**
     * Массив для записи.
     */
    private Object[] arraySet;
    /**
     * Индекс для записи данных в массив.
     */
    private int index = 0;

    /**
     * Конструктор класса с заданным пользователем размером массива.
     *
     * @param size размер.
     */
    public ArraySet(int size) {
        this.size = size;
        this.arraySet = new Object[this.size];
    }

    /**
     * Конструктор класса с размером массива заданным по умолчанию.
     */
    public ArraySet() {
        this.size = defaultSize;
        this.arraySet = new Object[size];
    }

    /**
     * Метод для добавления элементов в массив.
     *
     * @param value добавляемое значение.
     * */
    public void add(E value) {

        if (this.size == index) {
            incraseSizeSet();
        }

        if (checkSetValue(value)) {
            arraySet[index++] = value;
        }

    }

    /**
     * Метод для проверки содержимого массива.
     *
     * @param value проверяемое значение.
     * @return true если элемент отсутствует, false если элемент уже есть.
     */
    public boolean checkSetValue(E value) {
        boolean result = false;

        for (Object conteiner : arraySet) {
            if (value.equals(conteiner)) {
                result = false;
                break;
            } else {
                result = true;
            }
        }

        return result;
    }

    /**
     * Метод для определения количества заполненых элементов в смассиве.
     *
     * @return количество не null элементов.
     */
    public int lengthSet() {
        return this.index;
    }

    /**
     * Метод для увеличения размера массива в два раза.
     */
    public void incraseSizeSet() {
        int newSize = this.size * 2;
        Object[] newSizeSet = new Object[newSize];
        System.arraycopy(this.arraySet, 0, newSizeSet, 0, this.index);
        this.arraySet = newSizeSet;
        this.size = newSize;
    }

    /**
     * Метод итератор.
     *
     * @return итератор.
     */
    @Override
    public Iterator<E> iterator() {

        return new IteratorArraySet();
    }

    /**
     * Приватный класс для итератора.
     *
     * @param <E> дженерик.
     */
    private class IteratorArraySet<E> implements Iterator<E> {

        /**
         * Каретка для итератора.
         */
        private int slide = 0;

        /**
         * Метод hasNext.
         *
         * @return true если есть следующий элемент, false если нет.
         */
        @Override
        public boolean hasNext() {
            return slide < size && slide < index;
        }

        /**
         * Метод next.
         *
         * @return элемент.
         */
        @Override
        public E next() {

            if (slide > index) {
                throw new NoSuchElementException("Нет больше элементов.");
            }

            return (E) arraySet[slide++];
        }
    }
}
