package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 *Test RotateArray.
 *@author Shamkin Roman.
 *@since 10.05.2017.
 */
public class RotateArrayTest {
	/**
	*Тест 1.
	*Массив 2х2
	*/
	@Test
    public void whenRotateTwoRowTwoColArrayThenRotatedArray() {
       RotateArray rota = new RotateArray();
	   int[][] array = {{1, 2}, {3, 4}};
	   int[][] expectArray = rota.rotate(array);
	   int[][] resultArray = {{3, 1}, {4, 2}};
	   assertThat(resultArray, is(expectArray));
    }
	/**
	*Тест 2.
	*Массив 3х3
	*/
    @Test
    public void whenRotateThreeRowThreeColArrayThenRotatedArray() {
       RotateArray rota = new RotateArray();
	   int[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	   int[][] expectArray = rota.rotate(array);
	   int[][] resultArray = {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}};
	   assertThat(resultArray, is(expectArray));
    }
}