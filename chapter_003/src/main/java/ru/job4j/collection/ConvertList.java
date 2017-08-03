package ru.job4j.collection;


import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Конвертация двумерного массива в ArrayList и наоборот [#10035].
 *
 */
public class ConvertList {
    /**
     * Метод конвертации двумерного массива в ArrayList.
     * @param array двумерный массив
     * @return Коллекция полученная путем конвертации.
     */
    public List<Integer> toList(int[][] array) {
        ArrayList list = new ArrayList();

        for (int[] x : array) {
            for (int y : x) {
                list.add(y);
            }
        }

        return list;
    }

    /**
     * Метод коныертации коллекции в двумерный массив.
     * @param collection коллекция
     * @param row число строк.
     * @return двумерный массив равномерно разбитый на строки из элементов коллекции,
     * если нехватает элементов в строке, то добавляются нули.
     */
    public int[][] toArray(List<Integer> collection, int row) {
        int dlinaStroki;
        if (collection.size() % row == 0) {
            dlinaStroki = collection.size() / row;
        } else {
            dlinaStroki = collection.size() / row + 1;
        }

        ListIterator<Integer> iterator = collection.listIterator();

        int[][] array = new int[row][dlinaStroki];

        int temp = dlinaStroki;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < dlinaStroki; j++) {
                if (iterator.hasNext()) {
                    array[i][j] = iterator.next();

                } else {
                    temp = j;
                    break;
                }
            }
        }

        for (int j = temp; j < dlinaStroki; j++) {
            array[row - 1][j] = 0;
        }

        return array;
    }

    /**
     * Конвертация листа массивов в один лист Integer [#10037].
     * @param list коллекция массивов.
     * @return коллекция которая содержит все элементы из коллекции list.
     */
    public List<Integer> convert(List<int[]> list) {

        ArrayList<Integer> returnList = new ArrayList();

        for (int[] value : list) {
           for (int number : value) {
               returnList.add(number);
           }
        }

        return returnList;
    }
}
