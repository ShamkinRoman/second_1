package ru.job4j.testzadanie;


import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;

/**
 * Created by Up on 15.08.2017.
 */
public class TestPetr {
    /**
     * Проверка метода сортировки по возрастанию.
     */
    @Test
    public void whenSortedHigh() {

        Petrask petrask = new Petrask();

        String z1 = "K1\\SK1\\SSK1";
        String z2 = "K1\\SK1\\SSK2";
        String z3 = "K2\\SK2";
        String z4 = "K2\\SK1";


        String[] proverka = {z2, z1, z4, z3};


        List<String> otvet = petrask.setSortedHigh(proverka);

        assertThat(otvet.get(0), is("K1"));
        assertThat(otvet.get(1), is("K1\\SK1"));
        assertThat(otvet.get(2), is("K1\\SK1\\SSK1"));
        assertThat(otvet.get(3), is("K1\\SK1\\SSK2"));
        assertThat(otvet.get(4), is("K2"));
        assertThat(otvet.get(5), is("K2\\SK1"));
        assertThat(otvet.get(6), is("K2\\SK2"));

    }

    /**
     * Проверка метода сортировки по убыванию.
     */
    @Test
    public void whenSortLow() {

        Petrask petrask = new Petrask();

        String z1 = "K1\\SK1\\SSK1";
        String z2 = "K1\\SK1\\SSK2";
        String z3 = "K2\\SK2";
        String z4 = "K2\\SK1";

        String[] proverka = {z2, z1, z4, z3};


        List<String> otvet = petrask.setSortedLow(proverka);

        assertThat(otvet.get(0), is("K2"));
        assertThat(otvet.get(1), is("K2\\SK2"));
        assertThat(otvet.get(2), is("K2\\SK1"));
        assertThat(otvet.get(3), is("K1"));
        assertThat(otvet.get(4), is("K1\\SK1"));
        assertThat(otvet.get(5), is("K1\\SK1\\SSK2"));
        assertThat(otvet.get(6), is("K1\\SK1\\SSK1"));


    }


}
