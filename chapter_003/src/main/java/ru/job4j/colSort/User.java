package ru.job4j.colsort;

/**
 * Created by Up on 06.08.2017.
 */
public class User implements Comparable {
    /**
     * .Поле ИМЯ пользователя..
     */
    private String name;
    /**
     * Поле Возраст пользователя.
     */
    private int age;

    /**
     * Геттер Имени.
     *
     * @return имя
     */
    public String getName() {
        return this.name;
    }

    /**
     * Геттер Возраста.
     *
     * @return возраст
     */

    public int getAge() {
        return this.age;
    }

    /**
     * Конструктор класса User (пользователь).
     *
     * @param name имя
     * @param age  возраст
     */
    public User(String name, int age) {

        this.name = name;
        this.age = age;
    }

    /**
     * Преписанный метод, сортировки для коллекции по возрасту.
     *
     * @param temp объект сравниваемый с текущим.
     * @return результат сравнения ( 1 если первый больше второго; -1 если первый меньше второго; 0 если равны).
     */
    @Override
    public int compareTo(Object temp) {
        int result;
        User other = (User) temp;
        if (getAge() > other.getAge()) {
            result = 1;
        } else if (getAge() < other.getAge()) {
            result = -1;
        } else {
            result = 0;
        }
        return result;
    }

}

