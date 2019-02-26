package inputOut.consoleChat;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Chat {
    public static void main(String[] args) {
        //Любой текстовый файл в папке Properties. У меня он называется chat.txt
        File inputFile = new File(Chat.class.getClassLoader().getResource("chat.txt").getPath());
        new Chat().letsGo(inputFile);
    }

    public String getPhrase(File inputFile) {
        String result = null;
        try {
            RandomAccessFile accessFile = new RandomAccessFile(inputFile, "r");
            long position = ((long) (Math.random() * inputFile.length()));
            try {
                accessFile.seek(position);
                result = new String(accessFile.readLine().getBytes(StandardCharsets.ISO_8859_1), UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                accessFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void letsGo(File inputFile) {
        //Храним лог в файле logChat.txt в папке для временных файлов.
        File logChat = new File(System.getProperty("java.io.tmpdir") + "logChat.txt");
        BufferedWriter writer = null;
        String lineSeparator = System.getProperty("line.separator");
        // запись даты и времени нового чата
        try {
            writer = new BufferedWriter(new FileWriter(logChat, true));
            writer.write("============================================");
            writer.write(lineSeparator + LocalDateTime.now().toString() + lineSeparator);
            writer.write("============================================" + lineSeparator);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String userInput = "Start";
        String chatInput = "Begin";
        System.out.println("".length());
        boolean pause = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Начинай первый!");
        while (!userInput.equals("закончить")) {
            userInput = scanner.nextLine();
            if (userInput.equals("стоп")) {
                pause = true;
                chatInput = "<--меня попросили помолчать-->";
            }
            if (userInput.equals("продолжить")) {
                pause = false;
            }
            if (!pause) {
                chatInput = getPhrase(inputFile);
                System.out.println(chatInput);
            }
            try {
                writer.write("Пользователь сказал " + userInput + lineSeparator);
                writer.write("Программа ответила " + chatInput + lineSeparator);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
