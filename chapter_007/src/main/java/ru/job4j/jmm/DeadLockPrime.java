package ru.job4j.jmm;

/**
 * Демонстрация DeadLock.
 */
public class DeadLockPrime {
    /**
     * Значение поля number из второго пользователя, записывается в поле number первого.
     */
    private static class Transfer implements Runnable {
        /**
         * Первый.
         */
        private User userOne;
        /**
         * Второй.
         */
        private User userTwo;

        /**
         * Конструктор.
         *
         * @param userOne первый.
         * @param userTwo второй.
         */
        public Transfer(User userOne, User userTwo) {
            this.userOne = userOne;
            this.userTwo = userTwo;
        }

        /**
         * Метод демострирующий DeadLock.
         */
        public void trans() {
            synchronized (userOne) {
                try {
                    Thread.sleep(200); //Чтобы было время для второго потока залочить своего первого User.
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (userTwo) {
                    System.out.println(String.format("До изменения, значение было %s", userOne.getNumber()));
                    userOne.setNumber(userTwo.getNumber());
                    System.out.println(String.format("После изменения, значение стало %s", userOne.getNumber()));
                }
            }
        }

        /**
         * Ран.
         */
        @Override
        public void run() {

            trans();
        }
    }

    /**
     * Мэйн.
     *
     * @param args аргументы.
     */
    public static void main(String[] args) {

        User one = new User(3);
        User two = new User(7);

        Thread first = new Thread(new Transfer(one, two));
        Thread second = new Thread(new Transfer(two, one)); //Поменяли первого со вторым.

        first.start();
        second.start();

        try {
            first.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            second.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
