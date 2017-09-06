package ru.job4j.generic;

/**
 * Абстракнтый класс, имеющий поле id.
 */
public abstract class Base {
    /**
     * уникальный номер.
     */

    private String id;

    /**
     * Геттер id.
     *
     * @return уникальный номер.
     */
    public String getId() {
        return id;
    }

    /**
     * Сеттер id.
     *
     * @param id уникальный номер.
     */
    public void setId(String id) {
        this.id = id;
    }
}
