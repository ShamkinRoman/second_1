package socket.Bot;

import static org.junit.Assert.*;
import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ServerTest {

    private static final String lineSparator = System.getProperty("line.separator");

    public void serverSocketBotTest(String input, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server();
        server.letsGo(socket);
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenUserWriteExitThenBotStopAndSayBye() throws IOException {
        serverSocketBotTest("exit", Joiner.on(lineSparator).join( "Пока.","",""));
    }

    @Test
    public void whenUserWriteHelloTheBotGiveAnswer() throws IOException {
        serverSocketBotTest(Joiner.on(lineSparator).join("hello", "exit"),
                Joiner.on(lineSparator).join("Hello, nice to see you!",
                        "", "Пока.", "", ""));
    }

    @Test
    public void whenUserWriteSomeThingThenBotGiveAnswer() throws IOException {
        serverSocketBotTest(Joiner.on(lineSparator).join("Кракозябла не понятная", "exit"),
                Joiner.on(lineSparator).join("Не понял, может спросишь другое?", "", "Пока.", "", ""));
    }
}