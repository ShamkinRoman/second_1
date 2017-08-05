package ru.job4j.newtracker;


import java.util.Arrays;
import java.util.List;

/**
 * Created by Up on 29.06.2017.
 */
public class StartUIBase {

    /**
     * Создание ввода от пользователя.
     */
    private Input input;

    /**
     * Диапазон значений меню, для проверки на корректность ввода.
     */
    private List<Integer> ranges = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

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
     * Старт программы.
     * Вывод меню на экран.
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