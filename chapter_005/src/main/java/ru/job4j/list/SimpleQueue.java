package ru.job4j.list;

/**
 * Используя контейнер на базе связанного списка создать контейнер Queue.
 *
 * @param <T> дженерик.
 */
public class SimpleQueue<T> extends MyLinkedList<T> {
    /**
     * Медод для добавления элементов в очередь.
     *
     * @param t дженерик.
     */
    public void push(T t) {
        add(t);
    }

    /**
     * Метод для вызова последнего элемента и его удаления.
     *
     * @return последний элемент, если он отсутствует то null.
     */
    public T poll() {
        T result = null;
        if (this.get(0) != null) {
            result = this.get(0);
            remove(0);
        }
        return result;
    }
}
