package ru.job4j.calculator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 *Test Calculator.
 *@author Shamkin Roman.
 *@since 26.04.2017.
 */
public class CalculatorTest {
	/**
	*Add Test.
	*/
	@Test
	public void whenAddOnePlusOneThenTwo() {
		Calculator calc = new Calculator();
		calc.add(1D, 1D);
		double result = calc.getResult();
		double expected = 2D;
		assertThat(result, is(expected));
	}
	/**
	*div Test.
	*/
	@Test
	public void whenDivTenAtTwoThenFive() {
		Calculator calc = new Calculator();
		calc.div(10D, 2D);
		double result = calc.getResult();
		double expected = 5D;
		assertThat(result, is(expected));
	}
	/**
	*Substruct Test.
	*/
	@Test
	public void whenElevenSubstructSevenThenFour() {
		Calculator calc = new Calculator();
		calc.substruct(11D, 7D);
		double result = calc.getResult();
		double expected = 4D;
		assertThat(result, is(expected));
	}
	/**
	*Multiple Test.
	*/
	@Test
	public void whenThreeMultipleFiveThenFifthteen() {
		Calculator calc = new Calculator();
		calc.multiple(3D, 5D);
		double result = calc.getResult();
		double expected = 15D;
		assertThat(result, is(expected));
	}
}