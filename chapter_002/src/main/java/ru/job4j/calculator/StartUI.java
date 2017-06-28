package ru.job4j.calculator;

/**
 * Created by Администратор on 12.06.2017.
 */
public class StartUI {
    /**Создание трекера.*/
    //private Tracker tracker;
    /**
     * Создание ввода от пользователя.
     */
    private Input input;

    /**
     * Конструктор.
     *
     * @param input input
     */
    public StartUI(Input input) {
        this.input = input;
        //this.tracker = tracker;
    }

    /**
     * Дефолтный конструктор.
     */
    public StartUI() {
    }
    /**
     * Старт меню.
     */
    public void init() {

        Tracker tracker = new Tracker();
        MenuTracker menuTracker = new MenuTracker(this.input, tracker);
        menuTracker.fillaction();
        int key;
        do {
            menuTracker.show();
            key = Integer.valueOf(this.input.ask("Ваш выбор: "));
            menuTracker.select(key);
        } while (key != 7);
    }

    /**
     * main он и в Африке main, как есть.
     *
     * @param args args
     */
    public static void main(String[] args) {
        Input input = new ConsoleInput();
        new StartUI(input).init();
    }
}