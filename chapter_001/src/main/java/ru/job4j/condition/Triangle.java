package ru.job4j.condition;
/**
 *Class Triangle Task 3.3.
 *@author Shamkin Roman.
 *@since 01.05.2017.
 */
public class Triangle {
	/**Точка a. */
		private Point a;
	/**Точка b. */
		private Point b;
	/**Точка c. */
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
		this.c = null;
	}
	/** Метод для определения длины стороны.
	 *@param a - точка а
	 *@param b - точка b
	 *@return длина стороны
	*/
	public double dlina(Point a, Point b) {
		double dlinaX, dlinaY, dlina;
		dlinaX = Math.abs((a.getX() - b.getX()));
		dlinaY = Math.abs((a.getY() - b.getY()));
		dlina = Math.pow((Math.pow(dlinaX, 2) + Math.pow(dlinaY, 2)), 0.5);
		return dlina;
	}
	/**Вычисление площади.
	 *@return вычисленная площадь
	 */
	public double area() {
		if (this.c == null) {
			//@return -1 - отрицательное значение. т.к переданы две стороны
			return -1;
			}
		/** Вычисляем стороны ab, bc, ac */
		double ab, ac, bc;
		 ab = dlina(a, b);
		 bc = dlina(b, c);
		 ac = dlina(a, c);
		/**
		 *p полупериметр
		 *square площадь
		 */
		double p, square;
		//p=(A+B+C)/2
		p = (ab + bc + ac) / 2;
		//Площадь вычисляется по формуле Герона.
		//S=sqrt(p*(p-a)*(p-b)*(p-c))
		square = Math.pow((p * (p - ab) * (p - ac) * (p - bc)), 0.5);
		//@return S - площадь.
		return square;
	}
}