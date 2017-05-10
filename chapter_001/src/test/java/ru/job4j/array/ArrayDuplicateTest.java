package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 *Test DuplicateTest.
 *@author Shamkin Roman.
 *@since 10.05.2017.
 */
public class ArrayDuplicateTest {
	/**
	*Тест 1.
	*/
	@Test
	public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        ArrayDuplicate dubl = new ArrayDuplicate();
		String[] stroka = {"Привет", "Мир", "Привет", "Супер", "Мир"};
		String[] expectArray = dubl.remove(stroka);
	    String[] resultArray = {"Привет", "Мир", "Супер"};
	   assertThat(resultArray, is(expectArray));
    }
	/**
	*Тест 2.
	*Интересней тест проходить когда, в конце массива такие же значения, как и сравниваемые.
	*/
	@Test
	public void whenRemoveDuplicatesThenArrayWithoutDuplicate1() {
        ArrayDuplicate dubl = new ArrayDuplicate();
		String[] stroka = {"Niko", "Niko", "Alex", "Roman", "Alex", "Niko", "Niko", "Niko"};
		String[] expectArray = dubl.remove(stroka);
	    String[] resultArray = {"Niko", "Alex", "Roman"};
	   assertThat(resultArray, is(expectArray));
    }
	/**
	*Тест 3.
	*только одно имя.
	*/
	@Test
	public void whenOnlyOneName() {
        ArrayDuplicate dubl = new ArrayDuplicate();
		String[] stroka = {"Niko", "Niko", "Niko", "Niko", "Niko", "Niko", "Niko", "Niko", "Niko", "Niko", "Niko", "Niko", "Niko", "Niko", "Niko"};
		String[] expectArray = dubl.remove(stroka);
	    String[] resultArray = {"Niko"};
	   assertThat(resultArray, is(expectArray));
    }
}