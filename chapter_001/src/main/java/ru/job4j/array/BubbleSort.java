package ru.job4j.array;
/**
 *Class BubbleSort Task 5.1.
 *@author Shamkin Roman.
 *@since 08.05.2017.
 */
 public class BubbleSort {
	 /**
	 *Метод sort() сортирующий массив пузырьковым методом.
	 * Получилось только в таком виде, с двумя for циклами не получалось,
	 *получалось только без уменьшения длины пробегаемого диапазона, т.е.
	 final len = array.length;
	 for (int i=0;i<len;i++) {
			for (int j=0;j<len-2;j++) {
				if (num[j]>num[j+1]) {
					temp=num[j];
					num[j]=num[j+1];
					num[j+1]=temp;
				}
			}
			r--;
		}
	 *@param array входящий массив
	 *@return отсортированный масив
	 */
	 public int[] sort(int[] array) {
		int temp;
		int len = array.length;
		while (len > 0) {
			for (int j = 1; j < len; j++) {
				if (array[j] < array[j - 1]) {
					temp = array[j - 1];
					array[j - 1] = array[j];
					array[j] = temp;
				}
			}
			len--;
		}
		return array;
	 }
 }