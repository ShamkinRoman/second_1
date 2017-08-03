package ru.job4j.collection;

import org.junit.Test;

/**
 * Created by Администратор on 01.08.2017.
 */
public class TestAbstractColl {

    @Test
    public void whenArray() {
        ListMassiv test = new ListMassiv();
        test.arrList(100000);
        test.linkList(100000);
        test.woodList(100000);
    }
}