package ru.job4j;

import java.util.ArrayList;
import java.util.List;

public class Lamda002 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.stream().forEach(System.out::println);
        System.out.println(list.stream().filter((p)->p.equals("one")).count());
    }
}
