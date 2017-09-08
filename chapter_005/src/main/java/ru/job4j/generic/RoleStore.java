package ru.job4j.generic;

import java.util.NoSuchElementException;

/**
 * Класс для хранения Role.
 *
 * @param <Role> дженерик указанный явным образом.
 *               Данный класс служит для прототипа класса AbstractStore, на котором будут введены методы,
 *               общие для всех наследуемых классов и дженериков указаных в общем виде.
 */

public class RoleStore<Role extends Base> implements Store<Role> {

    /**
     * Начальный массив класса.
     */

    private SimpleArray<Role> simpleArray;
    /**
     * Размер начального массива по умолчанию.
     */

    private final int defaultSize = 10;

    /**
     * Конструктор класса с размером по умолчанию.
     */
    public RoleStore() {
        this.simpleArray = new SimpleArray<>(defaultSize);

    }

    /**
     * Метод добавления.
     *
     * @param role Role.
     */

    public void add(Role role) {

        simpleArray.add(role);

    }

    /**
     * Метод обновления значения в массиве.
     *
     * @param id   уникальный номер.
     * @param role Role.
     */

    public void update(String id, Role role) {

        simpleArray.update(findById(id), role);
        role.setId(id);

    }

    /**
     * Метод удаления значения в массиве.
     *
     * @param id уникальный номер.
     */

    public void delete(String id) {

        simpleArray.delete(findById(id));

    }

    /**
     * Метод получения значения из массива.
     *
     * @param id уникальный номер.
     * @return значение дженерика.
     */

    public Role get(String id) {

        return simpleArray.get(findById(id));

    }

    /**
     * Метод для поиса в массиве по уникальному номеру.
     *
     * @param id уникальный номер.
     * @return значение дженерика.
     */

    public Integer findById(String id) {

        Integer result = null;

        for (int i = 0; i < defaultSize; i++) {

            if (simpleArray.get(i) != null && (simpleArray.get(i).getId().equals(id))) {
                result = i;
                break;
            } else if (i == defaultSize - 1) {
                throw new NoSuchElementException("Не найдено");
            }
        }
        return result;

    }

}