package ru.job4j.set;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Реализовать коллекцию SimpleSet.
 *Коллекция должна обеспечивать void add(E e) и реализовывать Iterator<E>.
 *Коллекция не должна хранить дубликаты.
 *Set - внутри для хранения данных использует связный список.
 * @param <E> дженерик.
 */
public class LinkedSet<E> implements Iterable<E> {
    /**
     * Первый элемент.
     */
    private Node<E> first;
    /**
     * Последний элемент.
     */
    private Node<E> last;
    /**
     * Число элементов(длина множества).
     */
    private int length;

    /**
     * Дефолтный конструктор.
     */
    public LinkedSet() {
        last = new Node<E>(null, first, null);
        first = new Node<E>(null, null, last);
    }

    /**
     * Метод добавления элемента в множество.
     *
     * @param value добавляемое значение.
     */
    public void add(E value) {
        if (!checkSetValue(value)) {
            Node<E> prev = last;
            prev.item = value;
            last = new Node<E>(null, prev, null);
            prev.next = last;
            this.length++;

        }
    }

    /**
     * Возвращает размер множества.
     * @return длина множества.
     */
    public int length() {
        return this.length;
    }

    /**
     * Проверяет если ли элемент в множестве.
     * @param value добавляемое значение..
     * @return boolean.
     */
    public boolean checkSetValue(E value) {
        boolean result = false;
        Iterator<E> linkedSetIter = iterator();
        while (linkedSetIter.hasNext()) {
            if (value.equals(linkedSetIter.next())) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Итератор для множества.
     * @return итератор.
     */
    @Override
    public Iterator<E> iterator() {
        return new LinkedSetIterator<E>();
    }

    /**
     * Класс итератора для множества.
     * @param <E> дженерик.
     */
    private class LinkedSetIterator<E> implements Iterator<E> {
        /**
         * Точка счета.
         */
        private Node node = first;
        /**
         * Счетчик.
         */
        private int counter;

        /**
         * hasNext.
         * @return boolean.
         */
        @Override
        public boolean hasNext() {
            return counter < length && length != 0;
        }

        /**
         * next.
         * @return значение.
         */
        @Override
        public E next() {
            if (counter >= length) {
                throw new NoSuchElementException("Нет больше элементов.");
            }
            node = node.next;
            counter++;
            return (E) node.item;
        }
    }

    /**
     * Класс Node.
     *
     * @param <E> дженерик.
     */
    private class Node<E> {
        /**
         * Значение.
         */
        private E item;
        /**
         * Cледующий node.
         */
        private Node<E> next;
        /**
         * Предыдущий node.
         */
        private Node<E> prev;

        /**
         * Конструктор.
         *
         * @param item значение.
         * @param prev предыдущий node.
         * @param next следующий node.
         */
        Node(E item, Node<E> prev, Node<E> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
}

