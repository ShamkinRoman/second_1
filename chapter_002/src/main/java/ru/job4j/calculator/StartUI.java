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

   /**Диапазон значений меню, для проверки на корректность ввода.*/
    private int[] ranges = new int[]{1, 2, 3, 4, 5, 6, 7};
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
            key = Integer.valueOf(this.input.ask("Ваш выбор: ", ranges));
            //menuTracker.select(input.ask("Select: ", ranges));
            menuTracker.select(key);
        } while (7 != key);
    }

    /**
     * main он и в Африке main, как есть.
     *
     * @param args args
     */
    public static void main(String[] args) {
        Input input = new ValidateInput();
        new StartUI(input).init();
    }
}