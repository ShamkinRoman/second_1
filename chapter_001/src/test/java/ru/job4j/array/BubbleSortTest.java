package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 *Test Сортировка пузырьковым методом.
 *@author Shamkin Roman.
 *@since 10.05.2017.
 */
 public class BubbleSortTest {
	 /**
	*Тест 1.
	*сортировка 10 чисел
	*/
	@Test
	 public void whenSortArrayWithTenElementsThenSortedArray() {
		 BubbleSort bubble = new BubbleSort();
		 int[] array = {22, 11, 11, 33, 9, 32, 79, 41, 0, 6};
		 int[] expectArray = bubble.sort(array);
		 int[] resultArray = {0, 6, 9, 11, 11, 22, 32, 33, 41, 79};
		 assertThat(resultArray, is(expectArray));
	 }
	  /**
	*Тест 2.
	*сортировка 5 чисел
	*/
	@Test
	 public void whenSortArrayWithFiveElementsThenSortedArray() {
		 BubbleSort bubble = new BubbleSort();
		 int[] array = {-5, 10, -5, -20, 9};
		 int[] expectArray = bubble.sort(array);
		 int[] resultArray = {-20, -5, -5, 9, 10};
		 assertThat(resultArray, is(expectArray));
	 }
 }