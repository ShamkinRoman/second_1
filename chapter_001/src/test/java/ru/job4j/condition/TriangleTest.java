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
		Point pointA = new Point(0, 0);
		Point pointB = new Point(1, 0);
		Point pointC = new Point(0, 1);
		Triangle triangleABC = new Triangle(pointA, pointB, pointC);
		double result = triangleABC.area();
		double except = 0.5;
		assertThat(result, closeTo(except, 0.01));
	}
	/**Тест2.
	  *A(0;0) B(0;0) C(0;1)
	  *Square=0 потому что переданы две одинаковые точки.
	  */
	 @Test
	public void whenA00B00C01thenSquare0() {
		Point pointA = new Point(0, 0);
		Point pointB = new Point(0, 0);
		Point pointC = new Point(0, 1);
		Triangle triangleABCtest2 = new Triangle(pointA, pointB, pointC);
		double result = triangleABCtest2.area();
		double except = 0;
		assertThat(result, closeTo(except, 0.01));
	}
	/**Тест3.
	  *A(0;0) B(1;0)
	  *отсутствует точка С
	  *Square=-1
	  */
	 @Test
	public void whenA00B10CisnullThenSquareMinus1() {
		Point pointA = new Point(0, 0);
		Point pointB = new Point(1, 0);
		Triangle triangleABCtest3 = new Triangle(pointA, pointB);
		double result = triangleABCtest3.area();
		double except = -1;
		assertThat(result, closeTo(except, 0.01));
	}
}
