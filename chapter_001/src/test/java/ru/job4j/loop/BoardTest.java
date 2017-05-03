package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 *Test Board.
 *@author Shamkin Roman.
 *@since 03.05.2017.
 */
public class BoardTest {
	/**
	*Test 1.
	*width = 3, height = 3
	*/
    @Test
    public void whenPaintBoardWithWidthThreeAndHeightThreeThenStringWithThreeColsAndThreeRows() {
        Board board = new Board();
        String result = board.paint(3, 3);
        final String line = System.getProperty("line.separator");
        String expected = String.format("x x%s x %sx x%s", line, line, line);
        assertThat(result, is(expected));
		}
	/**Test 2.
	 *width = 4, height = 5
	 */
    @Test
    public void whenPaintBoardWithWidthFourAndHeightFiveThenStringWithFiveColsAndFourRows() {
		Board board = new Board();
        String result = board.paint(4, 5);
        final String line = System.getProperty("line.separator");
        String expected = String.format("x x %s x x%sx x %s x x%sx x %s", line, line, line, line, line);
        assertThat(result, is(expected));
    }
	/**Test 3.
	 *width = 5, height = 4
	 */
    @Test
    public void whenPaintBoardWithWidthFiveAndHeightFourThenStringWithFiveColsAndFourRows() {
		Board board = new Board();
        String result = board.paint(5, 4);
        final String line = System.getProperty("line.separator");
        String expected = String.format("x x x%s x x %sx x x%s x x %s", line, line, line, line);
        assertThat(result, is(expected));
    }
}