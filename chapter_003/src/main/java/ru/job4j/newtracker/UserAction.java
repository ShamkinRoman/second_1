package ru.job4j.newtracker;

/**
 * Created by Up on 26.06.2017.
 */
public interface UserAction {
    /**
     * Возврат значения ключа(меню).
     * @return key
     */
    int key();
    /**.
     * Метод исполнения
     * @param input input
     * @param tracker tracer
     */
    void execute(Input input, Tracker tracker);
    /**.
     * Информация для чего создан класс.
     * @return return
     */
    String info();
}
