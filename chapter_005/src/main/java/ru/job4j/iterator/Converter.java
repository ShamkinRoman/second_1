package ru.job4j.iterator;

import java.util.Iterator;

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
             * Итератор для итератора.
             */
            private Iterator<Integer> result = it.next();

            /**
             * Метод hasNext().
             * @return true если есть элементы впереди каретки, false если нет.
             */
            @Override
            public boolean hasNext() {

                return (it.hasNext() || result.hasNext());
            }

            /**
             * Переписанный метод next(). Возвращает элемент.
             *
             * @return возвращаемый элемент итератора.
             */
            @Override
            public Integer next() {

                Integer value = null;

                if (result.hasNext()) {

                    value = result.next();

                } else if (it.hasNext()) {

                    result = it.next();
                    value = result.next();
                }

                return value;
            }
        };

    }

}


