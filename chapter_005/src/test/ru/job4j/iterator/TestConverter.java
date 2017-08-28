package ru.job4j.iterator;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Проверка класса Convert.
 */
public class TestConverter {
    /**
     * Тест проверка метода convert.
     */

    @Test
    public void whenThreeVolumeInOne() {

        Converter converter = new Converter();

        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        List<Integer> list3 = Arrays.asList(7, 8, 9);

        Iterator<Integer> it1 = list1.iterator();
        Iterator<Integer> it2 = list2.iterator();
        Iterator<Integer> it3 = list3.iterator();

        //Обсложненный итератор.
        Iterator<Iterator<Integer>> iteratorIterator = Arrays.asList(it1, it2, it3).iterator();

        Iterator<Integer> audit = converter.convert(iteratorIterator);

        assertThat(audit.hasNext(), is(true));
        assertThat(audit.next(), is(1));

        audit.next();   //2
        audit.next();   //3
        audit.next();   //4
        audit.next();   //5
        audit.next();   //6

        assertThat(audit.hasNext(), is(true));
        assertThat(audit.next(), is(7));

        audit.next();   //8

        assertThat(audit.hasNext(), is(true));
        assertThat(audit.next(), is(9));

        assertThat(audit.hasNext(), is(false));


    }

    /**
     * Тест для проверки пустого итератора.
     */
    @Test
    public void whenEmptyListIterator() {

        Converter converter = new Converter();

        List<Integer> list1 = Arrays.asList();
        Iterator<Integer> it1 = list1.iterator();


        //Обсложненный итератор.
        Iterator<Iterator<Integer>> iteratorIterator = Arrays.asList(it1).iterator();

        Iterator<Integer> audit = converter.convert(iteratorIterator);

        assertThat(audit.hasNext(), is(false));

    }
}
