package singleton;

public class FirstSingleton {
    public void start() {
        int size = 100000;
        Thread t[] = new Thread[size];
        for (int i = 0; i < size; i++) {
            t[i] = new Thread(new R());
            t[i].start();
        }
        for (int i = 0; i < size; i++) {
            try {
                t[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Singleton.count);
    }
}

class R implements Runnable {
    @Override
    public void run() {
        Singleton.getInstance();
    }
}

class Singleton {
    public static int count = 0;
    private static volatile Singleton instance;

    private Singleton() {
        count++;
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}