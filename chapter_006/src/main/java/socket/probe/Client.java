package socket.probe;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        int port = 3333;
        String adres = "127.0.0.1";
        try {
            InetAddress address = InetAddress.getByName(adres);
            System.out.println("Подключаемся к адресу " + adres);
            Socket socket = null;
            try {
                socket = new Socket(address, port);
                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();

                DataInputStream in = new DataInputStream(input);
                DataOutput out = new DataOutputStream(output);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                String string = null;
                while (true){
                    System.out.println("Введите сообщение для отправки");
                    string = bufferedReader.readLine();
                    if (string.equals("stop")) {
                        socket.close();
                        break;
                    }
                    out.writeUTF(string);
                    ((DataOutputStream) out).flush();
                    string = in.readUTF();
                    System.out.println("Сервер прислал ответ "+ string);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
