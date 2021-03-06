package ru.job4j.max;
/**
 *Class Max (Maximum) Task 3.1.
 *@author Shamkin Roman.
 *@since 29.04.2017.
 */
public class Max {
	/**
	 *Method max which retunrned maximum number.
	 *@param first - arg
	 *@param second - arg
	 @return maximim
	 */
	public int max(int first, int second) {
		return first >= second ? first : second;
	}
	/**
	 *Method max which retunrned maximum number.
	 *@param first - arg
	 *@param second - arg
	 *@param third - arg
	 @return maximim from 3 number
	 */
	public int max(int first, int second, int third) {
		return max(max(first, second), third);
	}
}