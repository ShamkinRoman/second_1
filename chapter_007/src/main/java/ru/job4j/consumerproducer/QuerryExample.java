package ru.job4j.consumerproducer;

import java.util.ArrayList;

/**
 * Реализовать шаблон Producer Customer. [#1098].
 */
public class QuerryExample {

//    =======================================================

    /**
     * Реализация Блокирующей очереди.
     *
     * @param <E> дженерик.
     */
    private static class BlockQuerry<E> {

        private Object lock = new Object(); // блокировка.
        private int limit = 3; //размер очереди.
        private ArrayList<E> querry = new ArrayList<>(); //очередь со значениями.

        //Вставить в очередь значение.
        public void put(E value) throws InterruptedException {
            synchronized (lock) {

                while (querry.size() >= limit) {
                    System.out.println("Очередь заполена, ждите ....");
                    lock.wait();
                }
                querry.add(value);
                System.out.println(value);

                lock.notifyAll();
            }
        }

        //Изъять из очереди значение.
        public E take() throws InterruptedException {
            synchronized (lock) {
                E result = null;
                while (querry.size() == 0) {
                    System.out.println("Очередь пустая, ожидайте .....");
                    lock.wait();
                }
                result = querry.get(0);
                querry.remove(0);
                System.out.println(String.format("%s изъял, которое  %s", Thread.currentThread().getName(), result));
                lock.notifyAll();
                return result;
            }

        }

    }

//    ==================================================

    //Класс для заполнения очереди до 100.
    private static class Put implements Runnable {

        private BlockQuerry blockQuerry;

        public Put(BlockQuerry blockQuerry) {
            this.blockQuerry = blockQuerry;
        }

        @Override
        public void run() {


            for (Integer i = 0; i < 101; i++) {
                try {
                    blockQuerry.put(new String(Thread.currentThread().getName() + " добавил в очередь значение " + i));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(String.format("====== %s закончил свою работу. ======", Thread.currentThread().getName()));

        }
    }

    //    ===================================================

    //Класс для изъятия значений из очереди до 100 штук.
    private static class Take implements Runnable {

        private BlockQuerry blockQuerry;

        public Take(BlockQuerry blockQuerry) {
            this.blockQuerry = blockQuerry;
        }

        @Override
        public void run() {

            for (int i = 0; i < 101; i++) {

                try {
                    blockQuerry.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(String.format("====== %s закончил свою работу. ======", Thread.currentThread().getName()));


        }
    }

//    =================================================

    // МАЙН !!!
    // Количество потоко по заполнению очереди, должно равняться количеству потоков по изъятию из очереди.
    public static void main(String[] args) {

        BlockQuerry<Integer> blockQuerry = new BlockQuerry<>();

        Thread put = new Thread(new Put(blockQuerry));
        Thread put1 = new Thread(new Put(blockQuerry));
        Thread take = new Thread(new Take(blockQuerry));
        Thread take1 = new Thread(new Take(blockQuerry));

        put.start();
        put1.start();
        take.start();
        take1.start();
    }

}
