package inputOut.deleteWord;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class DeleteAbusesTest {
    DeleteAbuses del = new DeleteAbuses();
    String[] abuses = new String[]{"one", "two", "four"};

    @Test
    public void whenNotAbusesReturnFullString() {
        String fromChat = "Люблю грозу в начале мая.";
        ByteArrayInputStream in = new ByteArrayInputStream(fromChat.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        del.dropAbuses(in, out, abuses);
        assertThat(out.toString(), is(fromChat));
    }
    @Test
    public void whenAbusesReturnStringWithStars() {
        String fromChat = "Люблю one грозу two в начале мая. four";
        String control = "Люблю *** грозу *** в начале мая. ***";
        ByteArrayInputStream in = new ByteArrayInputStream(fromChat.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        del.dropAbuses(in, out, abuses);
        assertThat(out.toString(), is(control));
    }
}