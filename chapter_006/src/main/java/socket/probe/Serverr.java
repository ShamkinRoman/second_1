package socket.probe;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Serverr {
    public static void main(String[] args) {
        int port = 3333;
        try {
            System.out.println("Ждем подключение к серверу.");
            Socket socket = new ServerSocket(port).accept();

            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();

            DataInputStream in = new DataInputStream(input);
            DataOutput out = new DataOutputStream(output);
            String string = null;
            while(!socket.isConnected()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            while (socket.isConnected()) {

                    try {
                     string = in.readUTF();
                        if (!string.isEmpty()) {
                            System.out.println("От клиента пришло " + string);
                            System.out.println("Отправляем обратно");
                            out.writeUTF(string);
                            ((DataOutputStream) out).flush();
                        } else break;
                    } catch (EOFException eofe){
                        System.out.println("No correct exit");

                        break;
                    }

            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
