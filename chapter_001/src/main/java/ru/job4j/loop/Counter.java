package ru.job4j.loop;
/**
 *Class Counter Task 4.1.
 *@author Shamkin Roman.
 *@since 03.05.2017.
 */
public class Counter {
	/** Metod counter.
	 *@param start number
	 *@param finish number
	 *@return summa vseh chetnyh chisel ot 1 do num
	 */
	public int add(int start, int finish) {
		int count = 0;
		for (; start <= finish; start++) {
			if (start % 2 == 0) {
				count = count + start;
				}
				}
		return count;
	}
}