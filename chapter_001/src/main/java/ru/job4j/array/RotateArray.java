package ru.job4j.array;
/**
 *Class RotateArray Task 5.2.
 *@author Shamkin Roman.
 *@since 08.05.2017.
 */
 public class RotateArray {
	 /**
	 *Метод rotate() переворачивающий массив по часовой стрелке.
	 *@param array входящий двумерный массив
	 *@return перевернутый массив
	 *index переменная для переворачивания
	 */
	 public int[][] rotate(int[][] array) {
		 int dlina = array.length;
		 int shirina = array[0].length;
		int[][] arrayBack = new int[dlina][shirina];
		int index;
		for (int i = 0; i < dlina; i++) {
			index = 0;
			for (int j = shirina - 1; j >= 0; j--) {
				arrayBack[i][index] = array[j][i];
				index++;
			}
		}
		return arrayBack;
	 }
 }