package ru.job4j.collection;

import java.util.Collection;

/**
 * Абстрактный класс для добавления и удаления в коллекции.
 */

public abstract class AbstractColl {
    /**
     *
     * @param collection Колеекция в которую будут добавлятся элементы.
     * @param amount количество элементов которое будет бобавлено в коллекцию.
     * @return время затраченное на добавлене в коллекцию.
     */
    long add(Collection<String> collection, int amount) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i <= amount; i++) {
            collection.add(String.valueOf(i));
        }
        return System.currentTimeMillis() - startTime;
    }

    /**
     *
     * @param collection Коллекция из которой будет удалятся элементы.
     * @param amout количество удаляемых элементов
     * @return время затраченное на удалени из коллекци.
     */
    long delete(Collection<String> collection, int amout) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i <= amout; i++) {
            collection.remove(0);
        }
        return System.currentTimeMillis() - startTime;
    }

}
