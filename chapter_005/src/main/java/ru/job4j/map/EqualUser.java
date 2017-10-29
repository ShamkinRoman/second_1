package ru.job4j.map;

import java.util.Calendar;

/**
 * /**
 * Класс USER с переписанным методом EQUAL.
 */
public class EqualUser extends User {
    /**
     * Конструктор.
     *
     * @param name     Имя.
     * @param children количество детей.
     * @param birthday день рождения.
     */
    public EqualUser(String name, int children, Calendar birthday) {
        super(name, children, birthday);
    }

    /**
     * Переписанный метод EQUAL.
     *
     * @return false or true.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EqualUser equalUser = (EqualUser) o;

        if (getChildren() != equalUser.getChildren()) return false;
        if (getName() != null ? !getName().equals(equalUser.getName()) : equalUser.getName() != null) return false;
        return getBirthday() != null ? getBirthday().equals(equalUser.getBirthday()) : equalUser.getBirthday() == null;
    }
}

