package ru.job4j.max;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 *Test Max.
 *@author Shamkin Roman.
 *@since 26.04.2017.
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
}