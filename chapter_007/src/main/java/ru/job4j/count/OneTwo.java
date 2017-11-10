package ru.job4j.count;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Многопоточный счетчик.
 */
public class OneTwo {

    /**
     * Класс счетчик.
     */
    @ThreadSafe
    private static class Count {

        /**
         * Значение счетчика.
         */
        @GuardedBy("Increment")
        private int val;

        /**
         * Увеличивает на 1 значение счетчика.
         *
         * @return значение счетчика.
         */
        public synchronized int increment() {

            this.val++;

            System.out.println(String.format("Число запущенных потоков равно %s", this.val));
            return val;
        }
    }

    /**
     * Поток, который увеличивает значение счетчика на 1.
     */
    private static class MyClass implements Runnable {
        /**
         * Счетчик.
         */
        private Count count;

        /**
         * Конструктор.
         *
         * @param count счетчик.
         */
        public MyClass(Count count) {
            this.count = count;
        }

        /**
         * Ран.
         */
        @Override
        public void run() {
            count.increment();
        }
    }

    /**
     * Мэйн.
     *
     * @param args аргументы.
     */
    public static void main(String[] args) {

        Count count = new Count();

        for (int i = 0; i < 200; i++) {

            new Thread(new MyClass(count)).start();

        }

    }

}
