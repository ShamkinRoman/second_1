package ru.job4j.jmm;

/**
 * Класс с полем Integer.
 */
public class User {

    /**
     * Используется для подсчета.
     */
    private Integer number;

    /**
     * Конструктор.
     *
     * @param number начальное значение.
     */
    public User(Integer number) {
        this.number = number;
    }

    /**
     * Геттер.
     *
     * @return число.
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * Сеттер.
     *
     * @param number число.
     */
    public void setNumber(Integer number) {
        this.number = number;
    }
}
