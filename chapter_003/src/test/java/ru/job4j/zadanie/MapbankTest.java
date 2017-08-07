package ru.job4j.zadanie;

import org.junit.Test;



import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Up on 07.08.2017.
 * <p>
 * Тестирование Класса Mapbank.
 * <p>
 * Тестовое задание [#10038]
 */
public class MapbankTest {

    /**
     * Проверка метода, добавление пользователя.
     */
    @Test
    public void addUser() {

        Mapbank mapbank = new Mapbank();

        User user1 = new User("Вова", "Российский");
        User user2 = new User("Захар", "Эстонский");
        User user3 = new User("Андрей", "Китайский");

        mapbank.addUser(user1);
        mapbank.addUser(user3);
        mapbank.addUser(user2);

        assertThat(mapbank.getMap().containsKey(user1), is(true));
        assertThat(mapbank.getMap().containsKey(user2), is(true));
        assertThat(mapbank.getMap().containsKey(user3), is(true));

    }

    /**
     * Проверка метода, удаление пользователя.
     */
    @Test
    public void deleteUser() {
        Mapbank mapbank = new Mapbank();

        User user1 = new User("Вова", "Российский");
        User user2 = new User("Захар", "Эстонский");
        User user3 = new User("Андрей", "Китайский");

        mapbank.addUser(user1);
        mapbank.addUser(user2);
        mapbank.addUser(user3);

        //Проверяем, что пользователи добавились.

        assertThat(mapbank.getMap().containsKey(user1), is(true));
        assertThat(mapbank.getMap().containsKey(user2), is(true));
        assertThat(mapbank.getMap().containsKey(user3), is(true));

        // Удаляем.
        mapbank.deleteUser(user2);
        assertThat(mapbank.getMap().containsKey(user2), is(false));

        mapbank.deleteUser(user1);
        assertThat(mapbank.getMap().containsKey(user1), is(false));

        mapbank.deleteUser(user3);
        assertThat(mapbank.getMap().containsKey(user3), is(false));


    }

    /**
     * Проверка метода, добавление счета пользователю.
     */
    @Test
    public void addAccountToUser() {

        Mapbank mapbank = new Mapbank();

        User user1 = new User("Вова", "Российский");

        Account account1 = new Account(22, 1);
        Account account2 = new Account(33, 2);

        mapbank.addUser(user1);

        //Добавляем первый счет.
        mapbank.addAccountToUser(user1, account1);

        assertThat(mapbank.getMap().get(user1).get(0), is(account1));


        //Добавляем второй счет и убеждаемся, что у пользователя два счета.
        mapbank.addAccountToUser(user1, account2);
        assertThat(mapbank.getMap().get(user1).get(0), is(account1));
        assertThat(mapbank.getMap().get(user1).get(1), is(account2));


    }

    /**
     * Проверка метода, удаления счета у пользователя.
     */
    @Test
    public void deleteAccountFromUser() {
        Mapbank mapbank = new Mapbank();

        User user1 = new User("Вова", "Российский");

        Account account1 = new Account(22, 1);
        Account account2 = new Account(33, 2);

        mapbank.addUser(user1);

        //Добавляем первый счет.
        mapbank.addAccountToUser(user1, account1);

        assertThat(mapbank.getMap().get(user1).get(0), is(account1));


        //Добавляем второй счет и убеждаемся, что у пользователя два счета.
        mapbank.addAccountToUser(user1, account2);
        assertThat(mapbank.getMap().get(user1).get(0), is(account1));
        assertThat(mapbank.getMap().get(user1).get(1), is(account2));

        //Удаляем первый счет.
        mapbank.deleteAccountFromUser(user1, account1);

        //проверяем что у нас один счет и он равен Счету номер Два.
        assertThat(mapbank.getMap().get(user1).get(0), is(account2));
        assertThat(mapbank.getMap().get(user1).size(), is(1));

    }

    /**
     * Проверка метода, получить все счета у пользователя.
     */
    @Test
    public void getUserAccount() {
        Mapbank mapbank = new Mapbank();

        User user1 = new User("Вова", "Российский");

        Account account1 = new Account(22, 1);
        Account account2 = new Account(33, 2);
        Account account3 = new Account(44, 3);

        mapbank.addUser(user1);
        mapbank.addAccountToUser(user1, account1);
        mapbank.addAccountToUser(user1, account2);
        mapbank.addAccountToUser(user1, account3);

        List<Account> testAccount = mapbank.getUserAccount(user1);

        //Проверяем что имеется три счета и они равны заданным счетам.
        assertThat(testAccount.size(), is(3));
        assertThat(testAccount.get(0), is(account1));
        assertThat(testAccount.get(1), is(account2));
        assertThat(testAccount.get(2), is(account3));

    }

    /**
     * Проверка метода, возможна ли перевод денег со счета одного пользователя, на счет друго пользователя.
     * Или между счетами одного пользователя.
     */
    @Test
    public void transferMoney() {

        Mapbank mapbank = new Mapbank();

        User user1 = new User("Вова", "Российский");
        User user2 = new User("Захар", "Эстонский");

        Account account1 = new Account(22, 1);
        Account account2 = new Account(33, 2);

        mapbank.addUser(user1);
        mapbank.addAccountToUser(user1, account1);

        mapbank.addUser(user2);
        mapbank.addAccountToUser(user2, account2);

        //Количество денег на счету больше переводимого.
        double amount = 12;

        assertThat(mapbank.transferMoney(user1, account1, user2, account2, amount), is(true));

        //Количество денег на счету меньше переводимого.
        double amountMore = 120;

        assertThat(mapbank.transferMoney(user1, account1, user2, account2, amountMore), is(false));


    }

}