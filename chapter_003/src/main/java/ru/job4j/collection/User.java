package ru.job4j.collection;

/**
 * класс User с полями id, name, city..
 */
public class User {
    /**
     * Id пользователя.
     */
    private Integer id;
    /**
     * Имя пользователя.
     */
    private String name;
    /**
     * Город в котором живет пользователь.
     */
    private String city;

    /**
     * Геттер Id.
     *
     * @return id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Геттер Name.
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Геттер City.
     *
     * @return city
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Конструктор класса User.
     *
     * @param id   уникальный номер.
     * @param name имя.
     * @param city город.
     */
    public User(Integer id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }
}
