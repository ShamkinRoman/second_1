package ru.job4j.pool;

/**
 * Класс для выполнения в потоке.
 */
public class Work {

    //порядковый номер.
    private int number;

    //Конструктор.
    public Work(int number) {
        this.number = number;
    }

    //Возвращает строку с порядковым номером.
    public String printing() {

        return "Work № " + number;

    }
}