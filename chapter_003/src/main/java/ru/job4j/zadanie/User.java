package ru.job4j.zadanie;

/**
 * Created by Up on 07.08.2017.
 * Класс Пользователь.
 */
public class User {
    /**
     * Имя пользователя.
     */
    private String name;
    /**
     * Паспорт Пользователя.
     */
    private String pasport;

    /**
     * Геттер Имени.
     * @return имя
     */
    public String getName() {
        return this.name;
    }

    /**
     * Гетер паспорта.
     * @return паспорт.
     */

    public String getPasport() {
        return this.pasport;
    }

    /**
     * Конструктор класса.
     * @param name имя.
     * @param pasport паспорт.
     */
    public User(String name, String pasport) {

        this.name = name;
        this.pasport = pasport;
    }
}
