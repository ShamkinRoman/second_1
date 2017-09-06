package ru.job4j.generic;

import java.util.NoSuchElementException;

/**
 * Обобщенный класс для хранения дженерика.
 *
 * @param <E> дженерик.
 */

public class AbstractStore<E extends Base> implements Store<E> {

    /**
     * Начальный массив класса.
     */
    private SimpleArray<E> abstractStore;

    /**
     * Размер начального массива по умолчанию.
     */
    private final int defaultSize = 10;

    /**
     * Конструктор класса с размером по умолчанию.
     */
    public AbstractStore() {

        this.abstractStore = new SimpleArray<>(defaultSize);

    }

    /**
     * Метод добавления.
     *
     * @param e дженерик.
     */
    public void add(E e) {
        abstractStore.add(e);
    }

    /**
     * Метод обновления значения в массиве.
     *
     * @param id уникальный номер.
     * @param e  дженерик.
     */
    public void update(String id, E e) {

        abstractStore.update(findById(id), e);
        e.setId(id);

    }

    /**
     * Метод удаления значения в массиве.
     *
     * @param id уникальный номер.
     */

    public void delete(String id) {

        abstractStore.delete(findById(id));

    }

    /**
     * Метод получения значения из массива.
     *
     * @param id уникальный номер.
     * @return значение дженерика.
     */
    public E get(String id) {

        return abstractStore.get(findById(id));

    }

    /**
     * Метод для поиска в массиве по уникальному номеру.
     *
     * @param id уникальный номер.
     * @return значение дженерика.
     */
    public Integer findById(String id) {

        Integer result = null;

        for (int i = 0; i < defaultSize; i++) {

            if (abstractStore.get(i) != null && (abstractStore.get(i).getId().equals(id))) {
                result = i;
                break;
            } else if (i == defaultSize - 1) {
                throw new NoSuchElementException("Не найдено");
            }
        }
        return result;

    }

}
