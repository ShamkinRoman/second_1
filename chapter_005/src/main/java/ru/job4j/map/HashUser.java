package ru.job4j.map;

import java.util.Calendar;

/**
 * Класс USER с переписанным методом HASH.
 */
public class HashUser extends User {
    /**
     * Конструктор.
     *
     * @param name     Имя.
     * @param children количество детей.
     * @param birthday день рождения.
     */
    public HashUser(String name, int children, Calendar birthday) {
        super(name, children, birthday);
    }

    /**
     * Переписанный метод HASH.
     *
     * @return result.
     */
    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getChildren();
        result = 31 * result + getBirthday().hashCode();
        return result;
    }
}
