package socket.probe;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Serverr {
    public static void main(String[] args) {
        int port = 3333;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Ждем подключение к серверу.");
            Socket socket = serverSocket.accept();
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();

            DataInputStream in = new DataInputStream(input);
            DataOutput out = new DataOutputStream(output);
            String string = null;
            while (true) {
                string = in.readUTF();
                System.out.println("От клиента пришло " + string);
                System.out.println("Отправляем обратно");
                out.writeUTF(string);
                ((DataOutputStream) out).flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
