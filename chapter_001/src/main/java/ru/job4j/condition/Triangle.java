package ru.job4j.condition;
/**
 *Class Triangle Task 3.3.
 *@author Shamkin Roman.
 *@since 01.05.2017.
 */
 //Объявлем класс Triangle
public class Triangle {
	/** a Объявляем объект класса Point. */
	private Point a;
	/** b Объявляем объект класса Point. */
	private Point b;
	/** c Объявляем объект класса Point. */
	 private Point c;
	/**Объявляем метод Triangle, в качестве параметров передаем ему точки а, b, c.
	 *@param a - точка а
	 *@param b - точка b
	 *@param c - точка с
	 */
	public Triangle(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	/**Объявляем метод Triangle c двумя параметрами, на случай если передадут
	 * две точки, а не три.
	 *@param a - точка а
	 *@param b - точка b
	 */
	public  Triangle(Point a, Point b) {
		this.a = a;
		this.b = b;
		//присваеваем точке "с" пустое значение.
		this.c = null;
	}
	/**
	 * Метод для area() вычисления площади.
	 *@return зависит от того, сколько точек передали.
	 */
	public double area() {
		//проверяем, если передано две точки, возвращаем -1
		if (this.c == null) {
			//@return -1 - отрицательное значение.
			return -1;
			}
		/**Переменные для вычисления длины стороны AB
		 *dlinaAx длина по координате Х
		 *dlinaAy длина по координате Y
		 *dlinaA длина стороны AB
		 *Math.pow() возведение в степень
		 */
		double dlinaAx, dlinaAy, dlinaA;
		dlinaAx = Math.abs((this.a.getX() - this.b.getX()));
		dlinaAy = Math.abs((this.a.getY() - this.b.getY()));
		dlinaA = Math.pow((Math.pow(dlinaAx, 2) + Math.pow(dlinaAy, 2)), 0.5);
		/**Переменные для вычисления длины стороны BC
		 *dlinaBx длина по координате Х
		 *dlinaBy длина по координате Y
		 *dlinaB длина стороны BC
		 *Math.pow() возведение в степень
		 */
		double dlinaBx, dlinaBy, dlinaB;
		dlinaBx = Math.abs((this.c.getX() - this.b.getX()));
		dlinaBy = Math.abs((this.c.getY() - this.b.getY()));
		dlinaB = Math.pow((Math.pow(dlinaBx, 2) + Math.pow(dlinaBy, 2)), 0.5);
		/**Переменные для вычисления длины стороны АC
		 *dlinaCx длина по координате Х
		 *dlinaCy длина по координате Y
		 *dlinaC длина стороны AC
		 *Math.pow() возведение в степень
		 */
		double dlinaCx, dlinaCy, dlinaC;
		dlinaCx = Math.abs((this.a.getX() - this.c.getX()));
		dlinaCy = Math.abs((this.a.getY() - this.c.getY()));
		dlinaC = Math.pow((Math.pow(dlinaCx, 2) + Math.pow(dlinaCy, 2)), 0.5);
		/**
		 *p полупериметр
		 *square площадь
		 */
		double p, square;
		//p=(A+B+C)/2
		p = (dlinaA + dlinaB + dlinaC) / 2;
		//Площадь вычисляется по формуле Герона.
		//S=sqrt(p*(p-a)*(p-b)*(p-c))
		//Math.pow() возведение в степень
		square = Math.pow((p * (p - dlinaA) * (p - dlinaB) * (p - dlinaC)), 0.5);
		//@return S - площадь.
		return square;
	}
}