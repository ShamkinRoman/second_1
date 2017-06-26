package ru.job4j.strategy;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Created by Up on 18.06.2017..
 */
public class PaintTest {
    /**
     * Тест 1.
     * Квадрат
     */
    @Test
    public void whenSquare() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Paint paint = new Paint();
        paint.draw(new Square());
        String line = System.getProperty("line.separator");
        assertThat(out.toString(), is(String.format("xxx%sxxx%sxxx%s%s", line, line, line, line)));
    }

    /**
     * Тест 2.
     * Треугольник
     */
    @Test
    public void whenTriangle() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Paint paint = new Paint();
        paint.draw(new Triangle());
        String line = System.getProperty("line.separator");
        assertThat(out.toString(), is(String.format("  ^  %s ^^^ %s^^^^^%s%s", line, line, line, line)));
    }
}