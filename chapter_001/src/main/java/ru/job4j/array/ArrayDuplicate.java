package ru.job4j.array;
import java.util.Arrays;
/**
 *Class RotateArray Task 5.2.
 *@author Shamkin Roman.
 *@since 10.05.2017.
 */
 public class ArrayDuplicate {
	 /**
	 *Метод remove() удаляющий повторяющие слова в массиве.
	 *@param array входящий массив сторк
	 *@return перевернутый массив
	 */
	 public String[] remove(String[] array) {
		 /**temp для проверки итоговой строки, как метод меняет местами дубли, нужно для меня.*/
		String temp;
		int length = array.length;
		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j < length; j++) {
				/**работает только в таком виде вложенных if.
				 *Если объединить условия if ((array[ i ] == array[ j ]) && array[ j ] == array[length - 1])), то не работает.
				 */
				if (array[ i ] == array[ j ]) {
					if (array[ j ] == array[length - 1]) {
						j = i;
					} else {
						temp = array[ j ];
						array[ j ] = array[length - 1];
						array[length - 1] = temp;
					}
					length--;
				}
			}
			}
		return Arrays.copyOf(array, length);
	 }
 }