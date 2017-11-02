package ru.job4j.tread;

public class Tororo implements Runnable{

    private int value;

    public Tororo(int value) {
        this.value = value;
    }

    public void run1(int value) {

        System.out.println("Поток № "+value);

    }

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        run1(value);
    }

    public static void main(String[] args) throws InterruptedException {

        for (int i=1;i<10;i++) {

            (new Thread(new Tororo(i))).start();
            //Thread.sleep(1000);
        }

    }
}
