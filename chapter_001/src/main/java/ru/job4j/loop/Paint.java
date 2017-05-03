package ru.job4j.loop;
/**
 *Class Board Task 4.3.
 *@author Shamkin Roman.
 *@since 03.05.2017.
 */
 public class Paint {
	 /** Metod piramid.
	 *@param h vysota
	 *@return piramid in String
	 */
	public String piramid(int h) {
		StringBuilder builder = new StringBuilder();
		for (int i = 1; i <= h; i++)  {
			//Paint left side piramide include midle
			for (int j = 1; j <= h - i; j++) {
				builder.append(" ");
			}
			for (int m = 0; m < i; m++) {
				builder.append("^");
			}
			//Paint rigth side piramide without midle
				for (int m = 1; m < i; m++) {
				builder.append("^");
			}
			for (int k = 1; k <= h - i; k++) {
				builder.append(" ");
			}
			builder.append(System.getProperty("line.separator"));
		}
		String completedString = builder.toString();
		return completedString;
	}
 }