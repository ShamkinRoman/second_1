package ru.job4j.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Написать программу, которая замеряет время вставки в коллекцию большого.
 * количества случайных строк и удаления в коллекции первых n элементов для:
 *LinkedList
 *ArrayList
 *TreeSet
 *
 * использовал наследование из абстрактоного класса, хотя понимаю что можно было сделать и простой класс ( .
 */
public class ListMassiv extends AbstractColl {
    /**
     * Проверка колекции типа ArrayList.
     * @param amount количество элементов добавляемых в коллекцию.
     * сделал как входящий параметров, чтобы проще было писать тест.
     * в методе delete это значение уменьшил на 100, т.к. времени много нужно.
     */
    public void arrList(int amount) {
        ArrayList<String> collection = new ArrayList<>();
        System.out.println("ArrayLIST");
        System.out.println(String.format("Время создания коллекции: %s", add(collection, amount)));
        System.out.println(String.format("Время удаления коллекции: %s", delete(collection, amount / 100)));
    }

    /**
     Проверка колекции типа inkedListList.
     * @param amount количество элементов добавляемых в коллекцию.
     * сделал как входящий параметров, чтобы проще было писать тест.
     * в методе delete это значение уменьшил на 100, т.к. времени много нужно.
     */

    public void linkList(int amount) {
        LinkedList<String> collection = new LinkedList<>();
        System.out.println("LinkedLIST");
        System.out.println(String.format("Время создания коллекции: %s", add(collection, amount)));
        System.out.println(String.format("Время удаления коллекции: %s", delete(collection, amount / 100)));
    }

    /**
     * Проверка колекции типа TreeSet.
     * @param amount количество элементов добавляемых в коллекцию.
     * сделал как входящий параметров, чтобы проще было писать тест.
     * в методе delete это значение уменьшил на 100, т.к. времени много нужно.
     */
    public void woodList(int amount) {
        TreeSet<String> collection = new TreeSet<>();
        System.out.println("TreeSet");
        System.out.println(String.format("Время создания коллекции: %s", add(collection, amount)));
        long startTime = System.currentTimeMillis();
        for (int i = 0; i <= amount / 100; i++) {
            collection.remove(String.valueOf(i));
        }
        System.out.println(String.format("Время удаления коллекции: %s", (System.currentTimeMillis() - startTime)));
    }
}
