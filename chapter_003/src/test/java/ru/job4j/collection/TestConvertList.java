package ru.job4j.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Проверка задачи конвертации двумерного массива в коллекцию и обратно.
 */
public class TestConvertList {

    /**
     * Двумерный массив в коллекцию.
     */
    @Test
    public void whenArraytoList() {
        System.out.println();
        System.out.println("двумерный массив в список");
        int[][] proverka = {{1, 2, 3}, {4, 5, 6}};
        ConvertList convertList = new ConvertList();
        List<Integer> list = convertList.toList(proverka);
        System.out.println("Cписок содержит следующие элементы: ");
        for (int value : list) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    /**
     * Коллекцию в двумерный массив.
     */
    @Test
    public void whenListToArray() {
        System.out.println();
        System.out.println("Список в двумерный массив");
        ConvertList convertList = new ConvertList();
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 11; i++) {
            list.add(i);
        }
        int[][] proverka = convertList.toArray(list, 2);

        for (int[] x : proverka) {
            for (int y : x) {
                System.out.print(y + " ");
            }
            System.out.println();

        }
        System.out.println();

    }

    /**
     * Коллекция массивов в колекцию.
     */
    @Test
    public void whenListArrayInList() {
        System.out.println();
        System.out.println("Старт список массивов, в просто список");
        ConvertList convertList = new ConvertList();
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{7, 8, 9});
        list.add(new int[]{4, 5, 6});
        List<Integer> result = convertList.convert(list);
        System.out.println("Cписок содержит следующие элементы: ");
        for (int value : result) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
