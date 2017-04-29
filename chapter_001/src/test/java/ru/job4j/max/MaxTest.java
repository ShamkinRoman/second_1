package ru.job4j.max;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 *Test Max.
 *@author Shamkin Roman.
 *@since 26.04.2017.
 *Test 1-3 for testing 2 numbers.
 *Test 4-8 for testing 3 numbers.
 */
public class MaxTest {
	/**
	*Maximum Test1.
	*When first number is high.
	*/
	@Test
	public void whenTwoMoreOneThat() {
		Max maximum = new Max();
		int result = maximum.max(2, 1);
		int expected = 2;
		assertThat(result, is(expected));
	}
	/**
	*Maximum Test2.
	*When Second number is high.
	*/
	@Test
	public void whenEightMoreThreeThat() {
		Max maximumTwo = new Max();
		int result = maximumTwo.max(3, 8);
		int expected = 8;
		assertThat(result, is(expected));
	}
	/**
	*Maximum Test3.
	*When first number is equal two number.
	*/
	@Test
	public void whenFourEqualFourThat() {
		Max maximumFour = new Max();
		int result = maximumFour.max(4, 4);
		int expected = 4;
		assertThat(result, is(expected));
	}
	/**
	*Maximum Test4.
	*When first number is equal two number and 3 number.
	*/
	@Test
	public void whenFourEqualFourEqualFourThat() {
		Max maximumFour = new Max();
		int result = maximumFour.max(4, 4, 4);
		int expected = 4;
		assertThat(result, is(expected));
	}
	/**
	*Maximum Test5.
	*When First number is high.
	*/
	@Test
	public void whenTenMoreThreeAndFiveThat() {
		Max maximumTwo = new Max();
		int result = maximumTwo.max(10, 3, 5);
		int expected = 10;
		assertThat(result, is(expected));
	}
	/**
	*Maximum Test6.
	*When Second number is high.
	*/
	@Test
	public void whenElevenMoreThreeAndFiveThat() {
		Max maximumTwo = new Max();
		int result = maximumTwo.max(3, 11, 5);
		int expected = 11;
		assertThat(result, is(expected));
	}
	/**
	*Maximum Test7.
	*When Third number is high.
	*/
	@Test
	public void whenSixMoreThreeAndMinusFiveThat() {
		Max maximumTwo = new Max();
		int result = maximumTwo.max(3, -5, 6);
		int expected = 6;
		assertThat(result, is(expected));
	}
	/**
	*Maximum Test8.
	*When First and Third number is equal and high second number.
	*/
	@Test
	public void whenSevenMoretwoAndEqualSevenThat() {
		Max maximumTwo = new Max();
		int result = maximumTwo.max(7, 2, 7);
		int expected = 7;
		assertThat(result, is(expected));
	}
}