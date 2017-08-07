package ru.job4j.zadanie;

/**
 * Created by Up on 07.08.2017.
 * Класс Аккаунт.
 */
public class Account {
    /**
     * значение денег на счету.
     */
    private double money;
    /**
     * Реквизиты счета.
     */
    private double requisites;

    /**
     * Геттер money.
     * @return money
     */
    public double getMoney() {
        return this.money;
    }

    /**
     * Геттер реквизиты.
     * @return реквизиты.
     */

    public double getRequisites() {
        return this.requisites;
    }

    /**
     * Конструктор класса.
     * @param money значение денег.
     * @param requisites реквизиты.
     */
    public Account(double money, double requisites) {

        this.money = money;
        this.requisites = requisites;
    }
}
