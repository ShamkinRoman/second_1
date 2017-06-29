package ru.job4j.calculator;

/**
 * Created by Up on 29.06.2017.
 */
public class StartUIBase {
    /**Создание трекера.*/
    //private Tracker tracker;
    /**
     * Создание ввода от пользователя.
     */
    private Input input;

    /**Диапазон значений меню, для проверки на корректность ввода.*/
    private int[] ranges = new int[]{1, 2, 3, 4, 5, 6, 7};
    /**
     * Конструктор.
     *
     * @param input input
     */
    public StartUIBase(Input input) {
        this.input = input;
        //this.tracker = tracker;
    }

    /**
     * Дефолтный конструктор.
     */
    public StartUIBase() {
    }

    /**
     * Start.
     */
    public void initBaseAction() {

        Tracker tracker = new Tracker();
        MenuTrackerBase menuTrackerBase = new MenuTrackerBase(this.input, tracker);
        menuTrackerBase.fill();
        int key;
        do {
            menuTrackerBase.show();
            key = Integer.valueOf(this.input.ask("Ваш выбор: ", ranges));
            menuTrackerBase.select(key);
        } while (7 != key);
    }

    /**
     * main он и в Африке main, как есть.
     *
     * @param args args
     */
    public static void main(String[] args) {
        Input input = new ValidateInput();
        new StartUIBase(input).initBaseAction();
    }
}