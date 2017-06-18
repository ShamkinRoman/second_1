package ru.job4j.strategy;

/**
 * Created by Up on 18.06.2017.
 */
public class Square implements Shape {
    /**
     * Метод формирует квадрат в строку, не совсем квадрат визуально.
     * @return строка квадрата
     */
   public String pic() {
       StringBuilder builder = new StringBuilder();
       for (int i = 0; i < 3; i++) {
           builder.append("xxx");
           builder.append(System.getProperty("line.separator"));
       }
       return builder.toString();
   }
}
