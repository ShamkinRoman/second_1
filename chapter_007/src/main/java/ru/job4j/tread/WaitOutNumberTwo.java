package ru.job4j.tread;

import java.util.Date;

/**
 * 2. Сделать ожидание вывода. [#1017].
 */
public class WaitOutNumberTwo {
    /**
     * Класс для проверки пробелов и слов.
     */
    private static class Cheker extends Thread {
        /**
         * Проверяемая строка.
         */
        private String auditString;
        /**
         * Метод, если 1 то проверяет количество слов, иначе пробелы.
         */
        private int method;

        /**
         * Конструктор.
         * @param auditString проверяемая строка.
         * @param method метод.
         */
        public Cheker(String auditString, int method) {
            this.auditString = auditString;
            this.method = method;
        }

        /**
         * Проверка пробелов.
         * @return количество пробелов.
         */
        public int checkSpace() {

            int result = 0;
            char[] symbol = this.auditString.toCharArray();

            for (int i = 0; i < this.auditString.length(); i++) {

                if (symbol[i] == ' ') {
                    result++;
                    System.out.println(String.format("Число пробелов равно %s", result));
                }
                if (isInterrupted()) {
                    System.out.println(String.format("Поток %s был прерван.", getName()));
                    break;
                }

            }

            return result;
        }

        /**
         * Проверка слов.
         * @return количество слов.
         */
        public int checkWord() {

            int result = 0;
            char[] symbol = this.auditString.toCharArray();

            if (symbol[0] != ' ') {
                result++;
                System.out.println("Число слов равно 1 ");
            }

            for (int i = 1; i < symbol.length; i++) {

                if (symbol[i - 1] == ' ' && symbol[i] != ' ') {
                    result++;

                    System.out.println(String.format("Число слов равно %s", result));

                }
                if (isInterrupted()) {
                    System.out.println(String.format("Поток %s был прерван.", getName()));
                    break;
                }

            }


            return result;
        }

        /**
         * Метод ран.
         */
        public void run() {

            if (this.method == 1) {
                checkWord();
            } else {
                checkSpace();
            }


        }

    }

    /**
     * Метод майн.
     * @param args аргументы.
     */
    public static void main(String[] args) {




        long currentTime = System.currentTimeMillis();

        System.out.println("Программа для вычисления количества пробелов и слов в строке.");
        System.out.println(String.format("Время начало выполнения программы %s", (new Date()).toString()));

        String auditString = "первое второе   третье четвертое"; //5 пробелов.
        Cheker checkSpace = new Cheker(auditString, 0);
        Cheker checkWord = new Cheker(auditString, 1);
        checkSpace.start();
        checkWord.start();


        try {
            checkSpace.join();
            checkWord.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(String.format("Время окончания выполнения программы %s", (new Date()).toString()));
        System.out.println(String.format("Время выполнения программы - %,d ms", System.currentTimeMillis() - currentTime));
    }

}
