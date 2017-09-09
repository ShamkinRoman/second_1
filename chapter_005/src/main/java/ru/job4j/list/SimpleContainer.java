package ru.job4j.list;

/**
 * Интерфейс для DynamicList.
 * @param <E> дженерик.
 */

public interface SimpleContainer<E> extends Iterable<E> {

    /**
     * Метод для добавления элементов.
     * @param e добавляемое значение.
     */
    void add(E e);

    /**
     * Метод для получения значения по индексу.
     * @param index индекс.
     * @return получаемое значение.
     */
    E get(int index);

}
