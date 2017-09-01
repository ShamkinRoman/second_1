package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Проверка класса Дженерика SimpleArray.
 */
public class SimpleArrayTest {
    /**
     * Тест 1. Проверка метода ADD.
     */
    @Test
    public void whenAddNewElement() {

        SimpleArray<String> simpleArray = new SimpleArray<>(2);

        simpleArray.add("test");
        simpleArray.add("what");

        //Тест проходит и без equals.
        assertThat(simpleArray.get(0), is("test"));
        assertThat(simpleArray.get(1), is("what"));
    }

    /**
     * Тест 2. Проверка метода GET.
     */
    @Test
    public void whenGetElement() {

        SimpleArray<Integer> simpleArray = new SimpleArray<>(2);

        simpleArray.add(0);
        simpleArray.add(1);


        assertThat(simpleArray.get(0), is(0));
        assertThat(simpleArray.get(1), is(1));

    }

    /**
     * Тест 3. Проверка метода UPDATE. Когда замена, идет через поиск ОБЪЕКТА.
     */
    @Test
    public void whenStringUpdate() {

        SimpleArray<String> simpleArray = new SimpleArray<>(1);

        String oldValue = "Test";
        String newValue = "Status";

        simpleArray.add(oldValue);

        assertThat(simpleArray.get(0), is(oldValue));

        //Метод update через объект.
        simpleArray.update(oldValue, newValue);
        assertThat(simpleArray.get(0), is(newValue));

    }

    /**
     * Тест 4. Проверка метода UPDATE. Когда замена, идет через индекс заменяемого объекта.
     */
    @Test
    public void whenIntegerUpdate() {

        SimpleArray<Integer> simpleArray = new SimpleArray<>(1);

        Integer oldValue = 22;
        Integer newValue = 88;

        simpleArray.add(oldValue);

        assertThat(simpleArray.get(0), is(oldValue));

        // Метод update, через индекс и значение типа Integer.
        simpleArray.update(0, newValue);
        assertThat(simpleArray.get(0), is(newValue));

    }

    /**
     * Тест 5. Проверка метода DELETE. Удаление через индекс.
     */
    @Test
    public void whenIndexDelete() {

        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);

        simpleArray.add(0);
        simpleArray.add(1);
        simpleArray.add(2);


        //Удаление по индексу.
        simpleArray.delete(0);

        assertThat(simpleArray.get(0), is(1));
        assertThat(simpleArray.get(1), is(2));
    }

    /**
     * Тест 6. Проверка метода DELETE. Удаление через ОБЪЕКТ.
     */
    @Test
    public void whenOjectDelete() {

        SimpleArray<String> simpleArray = new SimpleArray<>(2);

        String value = "Test";
        String value1 = "What";

        simpleArray.add(value);
        simpleArray.add(value1);

        simpleArray.delete(value);

        assertThat(simpleArray.get(0), is(value1));


    }
}