package inputOut.checkArray;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class CheckOnNumberTest {
    CheckOnNumber check = new CheckOnNumber();

    @Test
    public void whenNumberEvenThenTrue() {
        ByteArrayInputStream stream1 = new ByteArrayInputStream("-00000".getBytes()); // :)
        ByteArrayInputStream stream2 = new ByteArrayInputStream("454".getBytes());
        ByteArrayInputStream stream3 = new ByteArrayInputStream("-22".getBytes());
        ByteArrayInputStream stream4 = new ByteArrayInputStream("12222".getBytes());
        assertThat(check.isNumberEven(stream1), is(true));
        assertThat(check.isNumberEven(stream2), is(true));
        assertThat(check.isNumberEven(stream3), is(true));
        assertThat(check.isNumberEven(stream4), is(true));
    }

    @Test
    public void whenNumberOddThenFalse() {
        ByteArrayInputStream stream1 = new ByteArrayInputStream("3".getBytes());
        ByteArrayInputStream stream2 = new ByteArrayInputStream("4543".getBytes());
        ByteArrayInputStream stream3 = new ByteArrayInputStream("-33".getBytes());
        ByteArrayInputStream stream4 = new ByteArrayInputStream("122221".getBytes());
        assertThat(check.isNumberEven(stream1), is(false));
        assertThat(check.isNumberEven(stream2), is(false));
        assertThat(check.isNumberEven(stream3), is(false));
        assertThat(check.isNumberEven(stream4), is(false));
    }
}