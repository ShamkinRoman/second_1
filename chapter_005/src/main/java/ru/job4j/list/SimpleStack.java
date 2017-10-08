package ru.job4j.list;

/**
 * Используя контейнер на базе связанного списка создать контейнер Stack.
 *
 * @param <T> дженерик.
 */
public class SimpleStack<T> extends MyLinkedList<T> {
    /**
     * Медод для добавления элементов в стек.
     *
     * @param e дженерик.
     */
    public void push(T e) {
        add(e);
    }

    /**
     * Метод для вызова последнего элемента и его удаления.
     *
     * @return последний элемент, если он отсутствует то null.
     */
    public T poll() {
        T result = null;
        if (this.get(size() - 1) != null) {
            result = this.get(size() - 1);
            remove(size() - 1);
        }
        return result;
    }

}
