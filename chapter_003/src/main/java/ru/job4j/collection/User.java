package ru.job4j.collection;

/**
 * класс User с полями id, name, city.
 */
public class User {
    public Integer id;
    public String name;
    public String city;

    /**
     * Конструктор класса User.
     * @param id уникальный номер.
     * @param name имя.
     * @param city город.
     */
    public User(Integer id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }
}
