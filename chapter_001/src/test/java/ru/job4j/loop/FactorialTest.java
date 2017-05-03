package ru.job4j.loop;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 *Test Factorial.
 *@author Shamkin Roman.
 *@since 03.05.2017.
 */
 public class FactorialTest {
	 /**
	*Test 1.
	*n=0 factorial=1
	*/
	@Test
	public void whenN0ThatFact1() {
		Factorial factorial = new Factorial();
		int result = factorial.calc(0);
		int expected = 1;
		assertThat(result, is(expected));
	}
	/**
	*Test 2.
	*n=1 factorial=1
	*/
	@Test
	public void whenN1ThatFact1() {
		Factorial factorial = new Factorial();
		int result = factorial.calc(1);
		int expected = 1;
		assertThat(result, is(expected));
	}
	/**
	*Test 3.
	*n=5 factorial=120
	*/
	@Test
	public void whenN5ThatFact120() {
		Factorial factorial = new Factorial();
		int result = factorial.calc(5);
		int expected = 120;
		assertThat(result, is(expected));
	}
	/**
	*Test 4.
	*n=-10 factorial=-1
	*only "+" number
	*/
	@Test
	public void whenNMinus10ThatFactMinus1() {
		Factorial factorial = new Factorial();
		int result = factorial.calc(-10);
		int expected = -1;
		assertThat(result, is(expected));
	}
}