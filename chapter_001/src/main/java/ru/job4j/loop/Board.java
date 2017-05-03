package ru.job4j.loop;
/**
 *Class Board Task 4.3.
 *@author Shamkin Roman.
 *@since 03.05.2017.
 */
public class Board {
	/** Metod paint.
	 *@param width shirina
	 *@param height vysota
	 *@return ChessBoard
	 */
	public String paint(int width, int height) {
		StringBuilder builder = new StringBuilder();
		for (int i = 1; i <= height; i++) {
			for (int j = 1; j <= width; j++) {
				if ((j + i) % 2 == 0) {
					builder.append("x");
				} else {
					builder.append(" ");
				}
			}
			builder.append(System.getProperty("line.separator"));
		}
		String completedString = builder.toString();
		return completedString;
	}
}