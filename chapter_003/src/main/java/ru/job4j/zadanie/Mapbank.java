package ru.job4j.zadanie;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Up on 07.08.2017.
 */
public class Mapbank {

    /**
     * Коллекция типа HashMap.
     */
    private Map<User, List<Account>> map = new HashMap<>();


    /**
     * Геттер коллекции.
     * @return коллекция.
     */
    public Map<User, List<Account>> getMap() {
        return this.map;
    }

    /**
     * Метод добавления нового пользователя.
     *
     * @param user добавляемый пользователь.
     */
    public void addUser(User user) {
        map.put(user, new ArrayList<>());
    }

    /**
     * Метод для удаления пользователя.
     *
     * @param user удаляемый пользователь.
     */
    public void deleteUser(User user) {
        map.remove(user);
    }

    /**
     * Метод для добавления Счета к пользователю.
     *
     * @param user    пользователь которому необходимо добавить счет.
     * @param account добавляемый счет.
     */

    public void addAccountToUser(User user, Account account) {

        List<Account> tempAccount = map.get(user);
        tempAccount.add(account);
        map.put(user, tempAccount);

    }

    /**
     * Метод для удаления Счета у пользователя.
     *
     * @param user    пользователь которому необходимо удалить счет.
     * @param account удаляемый счет
     */

    public void deleteAccountFromUser(User user, Account account) {

        List<Account> tempAccount = map.get(user);

        tempAccount.remove(account);

        map.put(user, tempAccount);
    }

    /**
     * Метод получения списка Счетов у пользователя.
     *
     * @param user пользователь у которого надо узнать счета.
     * @return список счетов.
     */

    public List<Account> getUserAccount(User user) {

        List<Account> tempAccount = new ArrayList<>();


        tempAccount = map.get(user);


        return tempAccount;
    }

    /**
     * Метод определяющий возможен ли перевод заданного количества денег с одного счета на другой.
     *
     * @param srcUser    Пользователь у которого будут переводится деньги.
     * @param srcAccount Счет с которого будет сниматься деньги.
     * @param dstUser    Пользователь которому будут переведены деньги.
     * @param dstAccount Счет на который будут переводится деньги.
     * @param amount     количество переводимых денег.
     * @return результат true если пользователи и счета существуют и достаточно денег на счете, false в остальных случаях.
     */
    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {

        /**
         * Валидация на проверку счета.
         */
        boolean result = false;
        boolean first = validation(srcUser, srcAccount);
        boolean two = validation(dstUser, dstAccount);

        if ((first && two) && (srcAccount.getMoney() >= amount)) {
            result = true;
        }


        return result;
    }


    /**
     * Валидация на проверку счета пользователя, т.е. существуетли такой счет у поьзователя.
     *
     * @param user    Проверяемый пользователь.
     * @param account проверяемый счет.
     * @return результат проверки true или false
     */
    public boolean validation(User user, Account account) {

        boolean result = false;

        for (User value : map.keySet()) {

            if (user.equals(value)) {

                for (Account tempAccount : map.get(value)) {
                    if (account.equals(tempAccount)) {
                        result = true;
                        break;
                    }
                }
            }
        }


        return result;
    }

    /**
     * Валидация на проверку пользователя, т.е. существуетли такой пользователь.
     *
     * @param user Проверяемый пользователь.
     * @return результат проверки true или false
     */

    public boolean validation(User user) {

        boolean result = false;

        for (User value : map.keySet()) {

            if (user.equals(value)) {
                result = true;
                break;
            }
        }
        return result;
    }
}