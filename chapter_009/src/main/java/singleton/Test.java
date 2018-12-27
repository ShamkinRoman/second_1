package singleton;

public class Test {
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        FirstSingleton singleton = new FirstSingleton();
        singleton.start();

    }
}
