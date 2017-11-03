package ru.job4j.tread;

import java.util.Date;

/**
 * Cчитать количество слов и пробелов в тексте [#1016].
 */
public class ChekerWordAndSpace {

    /**
     * Приватный класс, для подсчета пробелов и слов.
     */
    private static class SpaceCheck implements Runnable {
        /**
         * Определяет метод, если 1 подсчет слов, иначе пробелы.
         */
        private int metod;
        /**
         * Проверяемая строка.
         */
        private String input;

        /**
         * конструктор.
         *
         * @param input строка.
         * @param metod метод.
         */
        public SpaceCheck(String input, int metod) {
            this.input = input;
            this.metod = metod;
        }

        /**
         * Считает пробелы.
         *
         * @return
         */
        public int countSpace() {

            char[] symbol = this.input.toCharArray();

            int result = 0;

            int stringLength = symbol.length;

            for (int i = 0; i < stringLength; i++) {

                if (symbol[i] == ' ') {

                    result++;
                    System.out.println(String.format("На данный момент посчитано %s пробелов. ", result));
                }

            }
            return result;
        }

        /**
         * Считает слова.
         *
         * @return
         */
        public int countWord() {

            char[] symbol = this.input.toCharArray();

            int result = 1;

            int stringLength = symbol.length;

            for (int i = 0; i < stringLength; i++) {

                if (symbol[i] == ' ') {

                    result++;
                    System.out.println(String.format("На данный момент посчитано %s слов. ", result));
                }

            }

            return result;
        }

        /**
         * запуск потока.
         */
        @Override
        public void run() {
            if (this.metod == 1) {
                countWord();
            } else {
                countSpace();
            }
        }
    }

    /**
     * Метод майн.
     *
     * @param args аргументы.
     */
    public static void main(String[] args) {

        long currentTime = System.currentTimeMillis();

        System.out.println((new Date()).toString());

        String auditString = "Как интересно бывает программировать, но вот познать бы всю сущность бытия.";

        Thread spaceThread = new Thread(new SpaceCheck(auditString, 0));
        Thread wordThread = new Thread(new SpaceCheck(auditString, 1));


        spaceThread.start();
        wordThread.start();

        try {

            spaceThread.join();
            wordThread.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println((new Date()).toString());

        System.out.println(String.format("Время выполнения программы - %,d ms", System.currentTimeMillis() - currentTime));
    }

}
