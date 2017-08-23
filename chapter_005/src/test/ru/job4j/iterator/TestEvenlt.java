package ru.job4j.iterator;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Тест класса Evenlt.
 */
public class TestEvenlt {

    /**
     * Тест для проверки итератора, который выводит только четные числа.
     */
    @Test
    public void whenadd() {

        int[] audit = {4, 2, 1, 1, 8, 5, 6};

        Iterator it = new Evenlt(audit);


        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(8));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));

        assertThat(it.hasNext(), is(false));


    }
}
