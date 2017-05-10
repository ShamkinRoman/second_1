package ru.job4j.array;
/**
 *Class Turn Task 5.0.
 *@author Shamkin Roman.
 *@since 08.05.2017.
 */
public class Turn {
	/**
	 *Метод back() переворачивает массив.
	 *@param array входящий массив
	 *@return перевернутый массив
	 */
	public int[] back(int[] array) {
		int temp;
		int length = array.length;
				for (int i = 0; i < length; i++) {
			temp = array[i];
			array[i] = array[length - 1];
			array[length - 1] = temp;
			length--;
		}
		return array;
	}
}