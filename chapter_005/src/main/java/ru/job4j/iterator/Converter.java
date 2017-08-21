package ru.job4j.iterator;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;


/**
 * Реализовать класс с методом Iterator<Integer> convert(Iterator<Iterator<Integer>> it).
 */
public class Converter {
    /**
     * Метод конвертации многомерного итератора в одномерный.
     *
     * @param it Итератор итераторов.
     * @return одномерный итератор.
     */

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {


        return new Iterator<Integer>() {


            /**
             * Результирующий одномерный итератор, полученный путем преобразования многомерного.
             */
            private Iterator<Integer> result = oneVolume();

            /**
             * Метод hasNext().
             * @return true если есть элементы впереди каретки, false если нет.
             */
            @Override
            public boolean hasNext() {
                return result.hasNext();
            }

            /**
             * Переписанный метод next(). Возвращает следующий элемент.
             *
             * @return возвращаемый элемент массива.
             */
            @Override
            public Integer next() {
                return result.next();
            }

            /**
             * Метод для преобразования многомерного итератора, в одномерный.
             * @return одномерный итератор.
             */

            public Iterator<Integer> oneVolume() {

                Iterator<Integer> iter;

                List<Integer> list = new ArrayList<>();


                // it обсложненный итератор по условию задачи, входящий параметр.
                while (it.hasNext()) {
                    iter = it.next();

                    for (; iter.hasNext();) {
                        Integer value = iter.next();
                        list.add(value);
                    }

                }
                return list.iterator();
            }


        };
    }

}


