package sql3Parse;

import java.io.File;

public class file {

    public static void main(String[] args) {

        System.out.println("Начинаем КВН !!!");
        File we = new File(args[0]);
        String ss = we.getAbsolutePath();
        System.out.println(String.format("Абсолютный путь файли %s", ss));
        System.out.println("Пока");
    }

}
