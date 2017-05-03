package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *Test Paint.
 *@author Shamkin Roman.
 *@since 03.05.2017.
 */
public class PaintTest {
	/**
	*Test 1.
	*h=2
	* ^
	*^^^
	*/
    @Test
    public void whenPiramidWithHeightTwoThenStringWithTwoRows() {
        Paint paint1 = new Paint();
        String result = paint1.piramid(2);
		final String line = System.getProperty("line.separator");
		//+1 line.separator in end
        String expected = String.format(" ^ %s^^^%s", line, line);
        assertThat(result, is(expected));
    }
	/**
	*Test 2.
	*h=3
	*  ^
	* ^^^
	*^^^^^
	*/
    @Test
    public void whenPiramidWithHeightThreeThenStringWithThreeRows() {
        Paint paint = new Paint();
        String result = paint.piramid(3);
		final String line = System.getProperty("line.separator");
		//+1 line.separator in end
        String expected = String.format("  ^  %s ^^^ %s^^^^^%s", line, line, line);
        assertThat(result, is(expected));
    }
}