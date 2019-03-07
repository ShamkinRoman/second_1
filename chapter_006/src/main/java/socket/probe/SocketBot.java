package socket.probe;

import java.io.*;

public class SocketBot {
    public static void main(String[] args) {
        File inputFile = new File(SocketBot.class.getClassLoader().getResource("bot.txt").getPath());
        String find = "QWAS";
        char di = 92;
        String in = "START";
        BufferedReader inputKeyboard = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(di);
        try {

            while (!in.equals("stop")) {
                try {
                    in = inputKeyboard.readLine();
                    if (in.equals("stop")) {
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                while (find != null) {
                    try {
                        System.out.println(find = reader.readLine());
                        String[] finds = find.split(Character.toString('/'));
                        if (finds[0].contains(in)) {
                            System.out.println(finds[1]);
                            break;
                        } else {
                            find = reader.readLine();
                            if (find == null) {
                                System.out.println("Мдааа");
                                break;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
