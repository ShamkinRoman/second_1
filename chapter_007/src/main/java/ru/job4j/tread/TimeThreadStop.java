package ru.job4j.tread;

/**
 * 3. Реализовать механизм программнной остановки потока. [#1019].
 */
public class TimeThreadStop {

    /**
     * Поток останавливающий другой поток через указанное время.
     */
    static class Time implements Runnable {
        /**
         * Инспектируемый поток.
         */
        private Thread expectedThread;
        /**
         * Время начала отсчета.
         */
        private long startTime;
        /**
         * Через сколько мс останавливать.
         */
        private long setTime;

        /**
         * Конструктор.
         *
         * @param expectedThread поток.
         * @param startTime      время начала.
         * @param setTime        через сколько останавливать.
         */
        public Time(Thread expectedThread, long startTime, long setTime) {
            this.expectedThread = expectedThread;
            this.startTime = startTime;
            this.setTime = setTime;
        }

        /**
         * Задержка по времени.
         */
        public void checkTime() {

            try {
                Thread.sleep(setTime);
            } catch (InterruptedException e) {
                System.out.println("Поток спал. Выполнение потока прервано checkTime. ");
            }


            if (expectedThread.isAlive()) {
            expectedThread.interrupt();
        }

    }

        /**
         * Ран, как ран.
         */
        @Override
        public void run() {
            checkTime();


        }
    }

    /**
     * Подсчет символов.
     */
    static class CountChar implements Runnable {
        /**
         * Проверяемая строка.
         */
        private String auditString;

        /**
         * Конструктор.
         * @param auditString строка.
         */
        public CountChar(String auditString) {
            this.auditString = auditString;
        }

        /**
         * подсчет символов.
         * @return количество.
         */
        public int countCharInString() {
            int result = 0;
            char[] symbols = auditString.toCharArray();
            boolean flag = false;

            for (int i = 0; i < symbols.length; i++) {

                result++;
//Сдалал sleep, чтобы подсчет не был слишком быстрым.
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println(String.format("Поток был прерван из вне. посчитано %s символов из %s", result, auditString.length()));
                    flag = true;
                    break;
                }
            }
            if (!flag) {
            System.out.println(String.format("Число символов равно %s", result));
            }

            return result;
        }

        /**
         * Еще один ран :) .
         */
        @Override
        public void run() {

            countCharInString();
        }
    }

    /**
     * Куда ж мы без мэйна то? .
     * @param args аргументы.
     */
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        long expectTime = 130; //необходимо задать.

        String auditString = "ssadsadasdadddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddsdasdccvbb ";

        Thread count = new Thread(new CountChar(auditString));
        Thread time = new Thread(new Time(count, startTime, expectTime));


        count.start();
        time.start();
    }
}
