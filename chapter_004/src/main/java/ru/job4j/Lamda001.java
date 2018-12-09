package ru.job4j;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public class Lamda001 {
    public interface Operation {
        double calc(int left, int rigth);
    }

    public void multy(int start, int finish, int value, Operation op) {
        for (int i = start; i != finish; i++) {
            System.out.println(op.calc(value, i));

        }
    }

    public static void main(String[] args) {
        Lamda001 lamda001 = new Lamda001();
        lamda001.multy(0, 4, 10, new Operation() {
            @Override
            public double calc(int left, int rigth) {
                return left * rigth;
            }
        });
        /*
        -------------Lamda 001 -----------------------
         */
        System.out.println(" \n Lamda 02 start");
        Lamda001 lamda02 = new Lamda001();
        lamda02.multy(0, 5, 3, (value, index) -> value * index);

    /*
    Lamda 03
     */

        System.out.println(" \n Lamda 03 start");
        Lamda001 lamda03 = new Lamda001();
        lamda03.multy(0, 4, 5, (value, index) -> {
            int result = value * index;
            System.out.printf("Multy %s x %s = %s %n", index, value, result);
            return result;
        });
    /*
    Lamda 04
    */
        System.out.println(" \n Lamda 04 start");
        Lamda001 lamda04 = new Lamda001();
        lamda04.biFun(0,10,4, (value, index)->{
            double result = value*index;
            System.out.printf("Bi function multy %s x %s = %s \n",value, index,result);
            return result;
        }, result-> System.out.println(result));

    }

    /*
    --- Metod BiFunction and Consumer
     */

    public void biFun(int start, int finish, int value, BiFunction<Integer, Integer, Double> op, Consumer<Double> media) {
        for (int i = start; i != finish; i++) {
            media.accept(op.apply(value, i));

        }
    }

}
