package ru.job4j.pool;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;

/**
 * Реализовать ThreadPool [#1099].
 */
public class NewPool {

    //Блокировка.
    private Object lock = new Object();

    //Хранилище для потоков.
    @GuardedBy("this")
    private LinkedList<Work> listWork = new LinkedList<>();

    //Количество процессоров.
    private final int cpu = Runtime.getRuntime().availableProcessors();

    /**
     * Конструктор.
     * Интересная конечно реализация, на сколько она уместна?
     */

    public NewPool() {
        System.out.println("======================================");
        System.out.println(String.format("| В вашей системе доступно %s потока |", cpu));
        System.out.println("======================================");
        System.out.println("\n  Поехали !!! \n");

        for (int i = 0; i < cpu; i++) {
            new Thread(new Execute(i)).start();
        }
    }

//    ================================

    /**
     * Класс для запуска потоков.
     */
    @ThreadSafe
    private class Execute implements Runnable {

        //Номер по порядку.
        private int numberEx;

        //Конструктор.
        public Execute(int number) {
            this.numberEx = number;
        }

        //РАН!.
        @Override
        public void run() {

            while (true) {
                synchronized (lock) {
                    while (listWork.isEmpty()) {
                        try {
                            System.out.println(String.format(" Место в пуле № %s свободно...", numberEx));
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println(String.format("%s выполняется в потоке %s", listWork.poll().printing(), numberEx));
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Добавляем Класс Work в хранилище.
     *
     * @param work Work
     */
    public void add(Work work) {

        synchronized (lock) {
            listWork.add(work);
            lock.notifyAll();
        }
    }

    //Мэйн.
    public static void main(String[] args) throws InterruptedException {

        NewPool pool = new NewPool();

        for (int i = 0; i < 30; i++) {
            pool.add(new Work(i));
        }

        Thread.sleep(1000);

        for (int i = 30; i < 60; i++) {
            pool.add(new Work(i));
        }
    }
}
