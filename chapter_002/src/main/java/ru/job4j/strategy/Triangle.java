package ru.job4j.strategy;

/**
 * Created by Up on 18.06.2017.
 */
public class Triangle implements Shape {
    /**
     * Метод построения треугольника (а-ля пирамида).
     * @return строка
     */
    public String pic() {
        int h = 3;
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= h; i++)  {
            //Paint left side piramide include midle
            for (int j = 1; j <= h - i; j++) {
                builder.append(" ");
            }
            for (int m = 0; m < i; m++) {
                builder.append("^");
            }
            //Paint rigth side piramide without midle
            for (int m = 1; m < i; m++) {
                builder.append("^");
            }
            for (int k = 1; k <= h - i; k++) {
                builder.append(" ");
            }
            builder.append(System.getProperty("line.separator"));
        }
        return builder.toString();
    }
}
