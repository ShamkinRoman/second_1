package ru.job4j.iterator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Up on 23.08.2017.
 */
public class DoubleMassivTest {

    /**
     * Тест для проверки метода next().
     */

    @Test
    public void whenCheckNext() {

        DoubleMassiv it = new DoubleMassiv(new int[][]{{1, 2}, {3, 4, 5}, {6, 7, 8, 9}});

        //Пробегаемся по элементам массива.
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(6));
        assertThat(it.next(), is(7));
        assertThat(it.next(), is(8));
        assertThat(it.next(), is(9));
    }

    /**
     * Тест для проверки метода hasNext().
     */
    @Test
    public void whenCheckHasNext() {
        DoubleMassiv it = new DoubleMassiv(new int[][]{{1, 2}, {3, 4, 5}, {6, 7, 8, 9}});

        //Проверяем есть ли следующее значение.
        assertThat(it.hasNext(), is(true));
        //переводим каретку на следующий элемент.
        it.next(); //Значение 1.

        assertThat(it.hasNext(), is(true));
        it.next(); //Значение 2.

        assertThat(it.hasNext(), is(true));
        it.next(); //Значение 3.


        assertThat(it.hasNext(), is(true));
        it.next(); //Значение 4.
        it.next(); //Значение 5.
        it.next(); //Значение 6.
        it.next(); //Значение 7.
        it.next(); //Значение 8.
        it.next(); //Значение 9.


        //Десятого элемента нет в массиве, поэтому результат должен быть false.
        assertThat(it.hasNext(), is(false));


    }

}