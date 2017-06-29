package ru.job4j.calculator;

/**
 * Created by Up on 29.06.2017.
 */
public abstract class BaseAction {
    /**
     * Имя в меню.
     */
    private String name;
    /**
     * Номер в меню.
     */
    private int key;

    /**
     * Конструктор класса.
     * @param name имя.
     * @param key ключ
     */
    public BaseAction(String name, int key) {
        this.name = name;
        this.key = key;

    }

    /**
     * Реализация ключ.
     * @return номер в меню.
     */
    abstract int key();

    /**
     * Реализация выполнения.
     * @param input input
     * @param tracker tracker
     */
    abstract void execute(Input input, Tracker tracker);

    /**
     * Info.
     * @return return
     */
    public String info() {

        return String.format("%s. %s", this.key(), this.name);
    }
}
