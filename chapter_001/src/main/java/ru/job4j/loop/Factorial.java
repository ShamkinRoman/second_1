package ru.job4j.loop;
/**
 *Class Factorial Task 4.2.
 *@author Shamkin Roman.
 *@since 03.05.2017.
 */
 public class Factorial {
	 /** Metod calc.
	 *@param n number
	 *@return factorial
	 */
	 public int calc(int n) {
		 if (n < 0) {
			 return -1;
		 }
		 int fact = 1;
		 for (int i = 1; i <= n; i++) {
			 fact = fact * i;
		 }
		 return fact;
	 }
 }