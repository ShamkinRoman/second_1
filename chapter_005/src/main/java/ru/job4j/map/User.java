package ru.job4j.map;

import java.util.Calendar;

/**
 * Создать модель User и три поля String name, int children, Calendar birthday.
 */
public class User {
    /**
     * Имя.
     */
    private String name;
    /**
     * Количество детей.
     */
    private int children;
    /**
     * День рождения.
     */
    private Calendar birthday;

    /**
     * Конструктор.
     *
     * @param name     Имя.
     * @param children количество детей.
     * @param birthday день рождения.
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    /**
     * Геттер.
     *
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Геттер.
     *
     * @return children.
     */
    public int getChildren() {
        return children;
    }

    /**
     * Геттер.
     *
     * @return birthday.
     */
    public Calendar getBirthday() {
        return birthday;
    }
}
