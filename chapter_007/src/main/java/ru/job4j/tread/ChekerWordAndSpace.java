package ru.job4j.tread;

import java.util.Date;

public class ChekerWordAndSpace {

    private static class  SpaceCheck implements Runnable{

        private int metod;

        private String input;

        public SpaceCheck(String input, int metod) {
            this.input = input;
            this.metod=metod;
        }

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

        public int countWord () {

            char[] symbol = this.input.toCharArray();

            int result=1;

            int stringLength=symbol.length;

            for (int i=0;i< stringLength;i++ ){

                if(symbol[i]==' ') {

                    result++;
                    System.out.println(String.format( "На данный момент посчитано %s слов. ",result));
                }

            }

            return result;
        }


        @Override
        public void run() {
            if (this.metod==1) {
                countWord();
            } else {
                countSpace();
            }
        }
    }

    private static class ExpectedTime implements Runnable{

        private long time;



        private Thread first;
        private Thread second;

        public ExpectedTime(long time, Thread first, Thread second) {
            this.time = time;
            this.first = first;
            this.second = second;
        }

        @Override
        public void run() {
            isInterrunt();
        }

        public void isInterrunt() {

            int result=0;

            long idThread1 = first.getId();
            long idThread2 = second.getId();

            while((System.currentTimeMillis()-this.time)<100) {

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (first.isAlive()) {first.stop();result++;}
            if (first.isAlive()) {second.stop();result++;}
            if (result!=0) {
                System.out.println("Превышено время выполнения программы.");
            }

        }

    }

    public static void main(String[] args) {

        Date dateStart = new Date();
        Date dateFinish = new Date();

        long currentTime = System.currentTimeMillis();

        System.out.println(dateStart.toString());

        String auditString="Как интересно бывает программировать, но вот познать бы всю сущность бытия.";

        Thread firstThread = new Thread(new SpaceCheck(auditString,0));
        Thread secondThread = new Thread(new SpaceCheck(auditString,1));
        Thread thirdThread = new Thread(new ExpectedTime(currentTime,firstThread,secondThread));

        firstThread.start();
        secondThread.start();
        thirdThread.start();

        try {
            firstThread.join();
            secondThread.join();
            thirdThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(dateFinish.toString());
    }

}
