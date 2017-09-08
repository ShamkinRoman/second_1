package ru.job4j.list;

import java.util.Arrays;
import java.util.Iterator;

public class DynamicList<E> implements Iterable<E> {

    private final int defaultSize=10;

    private int position=0;

    private Object[] dataStore;


    public DynamicList() {
        this.dataStore = new Object[defaultSize];
    }

    public DynamicList(int userSize) {
        this.dataStore = new Object[userSize];
    }

    public E get(int index) {
        return (E) dataStore[index];
    }

    public void add (E e) {
        checkArray(position+1);
        dataStore[position++]=e;
    }

    public void checkArray(int i) {

        if (position>dataStore.length) {
            dataStore= Arrays.copyOf(dataStore,position*2);
        }
    }


    @Override
    public Iterator<E> iterator() {
        return null;
    }


}
