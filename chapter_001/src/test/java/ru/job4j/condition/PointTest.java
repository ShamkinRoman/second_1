package ru.job4j.condition;
//подключение библиотек теста
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 *Test Point.
 *@author Shamkin Roman.
 *@since 26.04.2017.
 */
public class PointTest {
	/**
	*Тест 1.
	*Когда точка принадлежит прямой.
	*х = 2
	*у = 7
	*a = 3
	*b= 1
	*7=3*2+1
	*/
	@Test
	public void whenX2A3B1thenY7() {
		//Создаем объект Point с координатами х = 2 у = 7.
		Point line = new Point(2, 7);
		//создаем переменную для результата о
		//принадлежности точки прякой с коэффициентами а = 3 b = 1.
		boolean result = line.is(3, 1);
		//создаем переменную для сравнения и заданным значение TRUE
		boolean expected = true;
		//сравниваем результат и вычисленное значение
		assertThat(result, is(expected));
	}
	/**
	*Тест 2.
	*Когда точка не принадлежит прямой.
	*х = 2
	*у = 22
	*a = 3
	*b= 1
	*22!=3*2+1
	*/
	@Test
	public void whenX2A3B1thenNotY7() {
		//Создаем объект Point с координатами х = 2 у = 22.
		Point line = new Point(2, 22);
		//создаем переменную для результата о
		//принадлежности точки прякой с коэффициентами а = 3 b = 1.
		boolean result = line.is(3, 1);
		//создаем переменную для сравнения и заданным значение FALSE
		boolean expected = false;
		//сравниваем результат и вычисленное значение
		assertThat(result, is(expected));
	}
	/**
	*Тест 3.
	*Когда точка принадлежит прямой.
	*х = 0
	*у = 0
	*a = 0
	*b = 1
	*1=0*0+1
	*/
	@Test
	public void whenX0A0B1thenY1() {
		//Создаем объект Point с координатами х = 0 у = 0.
		Point line = new Point(0, 1);
		//создаем переменную для результата о
		//принадлежности точки прякой с коэффициентами а = 0 b = 1.
		boolean result = line.is(0, 1);
		//создаем переменную для сравнения и заданным значение TRUE
		boolean expected = true;
		//сравниваем результат и вычисленное значение
		assertThat(result, is(expected));
	}
}