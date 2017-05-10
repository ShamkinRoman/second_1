package ru.job4j.zadanie;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 *Class ZadanieTest Task 6.0.
 *@author Shamkin Roman.
 *@since 10.05.2017.
 */
public class ContainsTest {
	/**
	 *Тест 1.
	 */
	 @Test
    public void whenOriginConsistOfSub() {
	Contains cons = new Contains();
	String origin = "Original string";
	String sub = "string";
	boolean result = cons.contains(origin, sub);
	boolean expected = true;
		assertThat(result, is(expected));
	}
	/**
	 *Тест 2.
	 */
	 @Test
    public void whenOriginNotConsistOfSub() {
	Contains cons = new Contains();
	String origin = "Original string";
	String sub = "vobla";
	boolean result = cons.contains(origin, sub);
	boolean expected = false;
		assertThat(result, is(expected));
	}
	/**
	 *Тест 3.
	 */
	 @Test
    public void whenOriginIsSub() {
	Contains cons = new Contains();
	String origin = "O";
	String sub = "O";
	boolean result = cons.contains(origin, sub);
	boolean expected = true;
		assertThat(result, is(expected));
	}
}