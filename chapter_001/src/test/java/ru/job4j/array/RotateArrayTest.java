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
	/**
	*Тест 3.
	*Массив 4х4
	*/
	@Test
    public void whenRotateFourArrayThenRotatedArray() {
       RotateArray rota = new RotateArray();
	   int[][] array = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
	   int[][] expectArray = rota.rotate(array);
	   int[][] resultArray = {{13, 9, 5, 1}, {14, 10, 6, 2}, {15, 11, 7, 3}, {16, 12, 8, 4}};
	   assertThat(resultArray, is(expectArray));
    }
	/**
	*Тест 4.
	*Массив 5х5
	*/
	@Test
    public void whenRotateFiveArrayThenRotatedArray() {
       RotateArray rota = new RotateArray();
	   int[][] array = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}};
	   int[][] expectArray = rota.rotate(array);
	   int[][] resultArray = {{21, 16, 11, 6, 1}, {22, 17, 12, 7, 2}, {23, 18, 13, 8, 3}, {24, 19, 14, 9, 4}, {25, 20, 15, 10, 5}};
	   assertThat(resultArray, is(expectArray));
    }
}