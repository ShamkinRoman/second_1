package socket.Bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Класс для Сервера. На нем иммитируется деятельность бота.
 */
public class Server {
    public static void main(String[] args) {
        int port = 3333;
        System.out.println("Wait to connection");
        try {
            Socket socket = new ServerSocket(port).accept();
            Server server = new Server();
            server.letsGo(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void letsGo(Socket socket){
        try {
            BotChat bot = new BotChat();
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String ask;
            System.out.println("Connection is successful");
            do {
                System.out.println("wait command ...");
                ask = in.readLine();
                System.out.println(ask);
                out.println(bot.getPhrase(ask)); //отсылаем ответ клиенту.
                out.println();
            } while (!"exit".equals(ask));
        } catch (IOException ee) {
            System.out.println(ee);
        }
    }
}
