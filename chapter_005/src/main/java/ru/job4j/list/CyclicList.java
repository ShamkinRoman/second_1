package ru.job4j.list;

import java.util.HashSet;

/**
 * Задан связанный список. Определить цикличность.
 */
public class CyclicList {

    /**
     * Метод для определения цикличности.
     *
     * @param first входной параметр(с которого начинается отсчет).
     * @return true если есть циклмчность, false если нет цикличности.
     */
    public boolean hasCycle(Node first) {

        HashSet<Node> hashSet = new HashSet<>();

        Node current = first;

        int i = 0;
        boolean result = false;

        while (current != null) {
            hashSet.add(current);
            i++;
            current = current.getNext();
            if (i > hashSet.size()) { // Если количество итераций больше размера множества, то была цикличность.
                result = true;
                break;
            }
        }
        return result;
    }
}

/**
 * Класс Node, на котором опробуется цикличность.
 *
 * @param <T> дженерик.
 */
class Node<T> {

    /**
     * Значение.
     */

    private T value;
    /**
     * Следующий элемент.
     */
    private Node<T> next;

    /**
     * Конструктор класса.
     *
     * @param value значение.
     */
    Node(T value) {
        this.value = value;
    }

    /**
     * Геттер следующего элемента.
     *
     * @return элемент.
     */
    public Node<T> getNext() {
        return this.next;
    }

    /**
     * Сеттер следующего элемента.
     *
     * @param next элемент.
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }
}