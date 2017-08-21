package ru.job4j.iterator;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Up on 21.08.2017.
 */
public class TestPrimelt {
    @Test
    public void whenCheckSimpleNumbers() {

        int[] audit = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

        Iterator it = new Primelt(audit);


        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(11));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(13));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(17));

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(19));

        assertThat(it.hasNext(), is(false));


    }
}
