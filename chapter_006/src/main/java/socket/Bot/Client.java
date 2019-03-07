package socket.Bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Клиент, с которго посылаются вопросы серверу.
 */
public class Client {
    public static void main(String[] args) {
        int port = 3333;
        String ip = "127.0.0.1";
        try {
            Socket socket = new Socket(InetAddress.getByName(ip), port);
            Client client = new Client();
            client.letsGo(socket);
        } catch (IOException ee) {
            System.out.println(ee);
        }
    }

    public void letsGo(Socket socket) {
        System.out.println("Connection is success.");
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner console = new Scanner(System.in);
            String inQuestion;
            String answer;
            System.out.println("Enter first phrase.");
            do {
                inQuestion = console.nextLine();
                out.println(inQuestion);
                answer = in.readLine();
                while (!answer.isEmpty()) {
                    System.out.println(answer);
                    answer = in.readLine();
                }
            } while (!inQuestion.equals("exit"));
        } catch (IOException ee) {
            ee.printStackTrace();
        }
    }
}