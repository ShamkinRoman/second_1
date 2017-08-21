package ru.job4j.iterator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Up on 08.08.2017.
 */
public class DoubleMassivTest {

    /**
     * Тест для проверки метода next().
     */

    @Test
    public void whenCheckNext() {

        DoubleMassiv it = new DoubleMassiv(new int[][]{{1, 2}, {3, 4}});

        //Пробегаемся по элементам массива.
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
    }

    /**
     * Тест для проверки метода hasNext().
     */
    @Test
    public void whenCheckHasNext() {
        DoubleMassiv it = new DoubleMassiv(new int[][]{{1, 2}, {3, 4}});

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

        //Пятого элемента нет в массиве, поэтому результат должен быть false.
        assertThat(it.hasNext(), is(false));


    }

}