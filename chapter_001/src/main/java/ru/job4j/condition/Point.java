package ru.job4j.condition;
/**
 *Class Point Task 3.2.
 *@author Shamkin Roman.
 *@since 28.04.2017.
 */
public class Point {
	/**
	 *Объявляем переменную x.
	 */
	private int x;
	/**
	 *Объявляем переменную y.
	 */
	private int y;
	/**
	 *Метод Poin() принимающий координаты X и Y.
	 *@param x - arg
	 *@param y - arg
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	/**
	 *Метод getX() возвращает координату X.
	 *@return x - координата.
	 */
	public int getX() {
		return this.x;
	}
	/**
	 *Метод getY() возвращающий координату Y.
	 *@return y - координата.
	 */
	public int getY() {
		return this.y;
	}
	/**
	 *Метод определяющий, принадлежит точка прямой или нет.
	 * Уравнение прямой y=a*x+b
	 *@param a - коэффициент а
	 *@param b - коэффициент b
	 @return возвращает ИСТИНА или ЛОЖЬ
	 */
	public boolean is(int a, int b) {
		return (a * this.x + b) == this.y;
	}
}