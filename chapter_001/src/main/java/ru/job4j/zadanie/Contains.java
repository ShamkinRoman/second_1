package ru.job4j.zadanie;
/**
 *Class Zadanie Task 6.0.
 *@author Shamkin Roman.
 *@since 10.05.2017.
 */
public class Contains {
	/**
	 *Метод contains() проверяющий является ли строка sub подстрокой origin.
	 *@param origin строка
	 *@param sub подстрока
	 *@return true или false
	 */
	public boolean contains(String origin, String sub) {
		/** Длина строки origin, sub.*/
		int lengthOrigin = origin.length();
		int lengthSub = sub.length();
		/**Преобразование строк в массив символов.*/
		char[] originChar = origin.toCharArray();
		char[] subChar = sub.toCharArray();
		/**переменная для результата. */
		boolean match = false;
		for (int i = 0; i < (lengthOrigin - lengthSub + 1); i++) {
			if (originChar[i] == subChar[0]) {
				for (int j = 0; j < lengthSub; j++) {
					if (originChar[i + j] != subChar[j]) {
						break;
					} else {
						if (j == lengthSub - 1) {
							match = true;
							}
						}
					}
				}
			}
		return match;
	}
}