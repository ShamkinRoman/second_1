package ru.job4j.list;
//Я вернулся..
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Создать контейнер на базе связанного списка.
 *
 * @param <E>
 */
public class MyLinkedList<E> implements SimpleContainer<E> {

    /**
     * Приватный класс для создания списков.
     *
     * @param <E> дженерик.
     */
    private class Record<E> {
        /**
         * дженерик.
         */
        private E element;
        /**
         * Класс для дженерика.
         */
        private Record<E> next;
        /**
         * Класс для дженерика.
         */
        private Record<E> last;

        /**
         * Конструктор класса.
         *
         * @param element Текущий элемент.
         * @param next    Следующий элемент.
         * @param last    Предыдущий элемент.
         */
         Record(E element, Record<E> next, Record<E> last) {
            this.element = element;
            this.next = next;
            this.last = last;
        }
    }

    /**
     * Число элементов в списке.
     */
    private int size = 0;
    /**
     * Первый элемент.
     */
    private Record<E> firstRecord;
    /**
     * Последний элемент.
     */
    private Record<E> lastRecord;

    /**
     * Метод для поиска элементов в списке.
     *
     * @param index индекс.
     * @return Найденый элемент.
     */
    private Record<E> find(int index) {

        checkIndex(index);
        Record<E> element;

        if (index < size / 2) {
            element = firstRecord;
            for (int position = 0; position < index; position++) {
                element = element.next;
            }

        } else {
            element = lastRecord;
            for (int position = size - 1; position > index; position--) {
                element = element.last;
            }
        }

        return element;
    }

    /**
     * Проверка диапазона индекса.
     *
     * @param index индекс.
     */
    public void checkIndex(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("нет элементов в заданном диапазоне");
        }
    }

    /**
     * Метод добавления в список.
     *
     * @param e добавляемое значение.
     */
    @Override
    public void add(E e) {

        Record<E> newRecord = new Record<>(e, null, lastRecord);

        if (lastRecord == null) {
            firstRecord = newRecord;
        } else {
            newRecord.next = newRecord;
        }
        lastRecord = newRecord;
        size++;

    }

    /**
     * Определение размера списка.
     *
     * @return размер списка.
     */
    public int size() {
        return size;
    }

    /**
     * Метод для получения значения элемента по индексу.
     *
     * @param index индекс.
     * @return значение элемента.
     */
    @Override
    public E get(int index) {
        return find(index).element;
    }

    /**
     * Итнератор списка.
     *
     * @return итератор.
     */
    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    /**
     * Класс для итератора списка.
     */
    private class Itr implements Iterator<E> {

        /**
         * счетких элементов.
         */
        private int caretka = 0;

        /**
         * Метод для опрнделения следующего значения.
         *
         * @return true если есть следующее значение, false если нет следующего значения.
         */
        @Override
        public boolean hasNext() {
            return caretka != size;
        }

        /**
         * Метод next.
         *
         * @return элемент и переводит каретку на следующий элемент.
         */
        @Override
        public E next() {
            try {
                return get(caretka++);
            } catch (NullPointerException npe) {
                throw new NoSuchElementException("Не найдено");

            }
        }
    }
}

