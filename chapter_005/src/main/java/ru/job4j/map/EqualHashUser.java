package ru.job4j.map;

import java.util.Calendar;

/**
 * Класс USER с переписанными методоми HASH and Equal.
 * Метод equal наследуется от класса EqualUser.
 */
public class EqualHashUser extends EqualUser {
    /**
     * Конструктор.
     *
     * @param name     Имя.
     * @param children количество детей.
     * @param birthday день рождения.
     */
    public EqualHashUser(String name, int children, Calendar birthday) {
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
