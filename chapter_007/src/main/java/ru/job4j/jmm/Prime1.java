package ru.job4j.jmm;

/**
 * Класс иллюстрирующий проблемы с многопоточностью.
 */
public class Prime1 extends Thread {

    /**
     * Класс в котором будет использоваться переменная для одновременного доступа.
     */
    private User user;
    /**
     * Ограничение по суммурованию.
     */
    private int limit;

    /**
     * Конструктор.
     *
     * @param user  User.
     * @param limit лимит.
     */
    public Prime1(User user, int limit) {
        this.user = user;
        this.limit = limit;
    }

    /**
     * Инкремент.
     */
    public void inrcement() {

        for (int i = 0; i < limit; i++) {

            user.setNumber(user.getNumber() + 1);
        }
    }

    /**
     * Ран.
     */
    @Override
    public void run() {
        inrcement();
    }
}
