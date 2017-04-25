package ru.job4j.calculator;
/**
 *Class Calculate решение задачи части 002 урок 3.
 *@author Shamkin Roman.
 *@since 26.04.2017.
 */
public class Calculator {
	/**
	 * Create result.
	 */
	private double result;
	/**
	 *Method Add.
	 *@param first - arg
	 *@param second - arg
	 */
	public void add(double first, double second) {
		this.result = first + second;
	}
	/**
	 *Method div.
	 *@param first - arg
	 *@param second - arg
	 */
	public void div(double first, double second) {
		this.result = first / second;
	}
	/**
	 *Method substruct.
	 *@param first - arg
	 *@param second - arg
	 */
	public void substruct(double first, double second) {
		this.result = first - second;
	}
	/**
	 *Method multiple.
	 *@param first - arg
	 *@param second - arg
	 */
	public void multiple(double first, double second) {
		this.result = first * second;
	}
	/**
	 *Method Return.
	 *@return result - result
	 */
	public double getResult() {
		return this.result;
	}
}