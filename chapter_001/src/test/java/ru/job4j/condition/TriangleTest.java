package ru.job4j.condition;
//подключение библиотек теста
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;
/**
 *Test Triangle Task 3.3.
 *@author Shamkin Roman.
 *@since 01.05.2017.
 */
//Объявлем класс TestTriangle
 public class TriangleTest {
	 /**Тест1.
	  *A(0;0) B(1;0) C(0;1)
	  *Square=0,5
	  */
	 @Test
	public void whenA00B10C01thenSquare05() {
		/**PointA Создаем объект pointA класса Point с координатами х = 0 у = 0. */
		Point pointA = new Point(0, 0);
		/**Создаем объект pointB класса Point с координатами х = 1 у = 0. */
		Point pointB = new Point(1, 0);
		/**Создаем объект pointC класса Point с координатами х = 0 у = 1. */
		Point pointC = new Point(0, 1);
		/**Создаем объект triangleABC класса Triangle с точками A, B, C. */
		Triangle triangleABC = new Triangle(pointA, pointB, pointC);
		//создаем переменную для площади
		double result = triangleABC.area();
		/**создаем переменную для сравнения с вычисленным заранее значением равным 0.5 */
		double except = 0.5;
		/**сравниваем результат и вычисленное значение */
		assertThat(result, closeTo(except, 0.01));
	}
	/**Тест2.
	  *A(0;0) B(0;0) C(0;1)
	  *Square=0 потому что переданы две одинаковые точки.
	  */
	 @Test
	public void whenA00B00C01thenSquare0() {
		/**PointA Создаем объект PointA Point с координатами х = 0 у = 0. */
		Point pointA = new Point(0, 0);
		/**Создаем объект B Point с координатами х = 0 у = 0. */
		Point pointB = new Point(0, 0);
		/**Создаем объект C Point с координатами х = 0 у = 1. */
		Point pointC = new Point(0, 1);
		/**Создаем объект TriangleABCtest2 с точками A, B, C. */
		Triangle triangleABCtest2 = new Triangle(pointA, pointB, pointC);
		//создаем переменную для площади
		double result = triangleABCtest2.area();
		/**создаем переменную для сравнения с вычисленным заранее значением равным 0 */
		double except = 0;
		/**сравниваем результат и вычисленное значение */
		assertThat(result, closeTo(except, 0.01));
	}
	/**Тест3.
	  *A(0;0) B(1;0)
	  *отсутствует точка С
	  *Square=-1
	  */
	 @Test
	public void whenA00B10CisnullThenSquareMinus1() {
		/**PointA Создаем объект pointA Point с координатами х = 0 у = 0. */
		Point pointA = new Point(0, 0);
		/**Создаем объект pointB с координатами х = 1 у = 0. */
		Point pointB = new Point(1, 0);
		/**Создаем объект Triangle с точками A, B. */
		Triangle triangleABCtest3 = new Triangle(pointA, pointB);
		//создаем переменную для площади
		double result = triangleABCtest3.area();
		/**создаем переменную для сравнения с вычисленным заранее значением равным 0.5 */
		double except = -1;
		/**сравниваем результат и вычисленное значение */
		assertThat(result, closeTo(except, 0.01));
	}
}
