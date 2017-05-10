package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 *Test Turn.
 *@author Shamkin Roman.
 *@since 10.05.2017.
 */
public class TurnTest {
	/**
	*Тест 1.
	*четный одномерный массив
	*{22, 12, 33, 87}
	*/
    @Test
    public void whenTurnArrayWithEvenAmountOfElementsThenTurnedArray() {
		Turn turn = new Turn();
		int[] array = {22, 12, 33, 87};
		int[] expectArray = turn.back(array);
		int[] resultArray = {87, 33, 12, 22};
		assertThat(resultArray, is(expectArray));
	}
	/**
	*Тест 1.
	*нечетный одномерный массив
	*{22, 11, 12, 33, 87}
	*/
    @Test
    public void whenTurnArrayWithOddAmountOfElementsThenTurnedArray() {
		Turn turn = new Turn();
		int[] array = {22, 11, 12, 33, 87};
		int[] expectArray = turn.back(array);
		int[] resultArray = {87, 33, 12, 11, 22};
		assertThat(resultArray, is(expectArray));
    }
}