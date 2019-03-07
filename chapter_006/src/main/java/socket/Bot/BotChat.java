package socket.Bot;

import java.io.*;

/**
 * Класс для иммитации разговора с ботом.
 * Словарь для бота хранится в папке с ресурсами.
 * Основная проблема этого класса постоянное открывание файла для чтения при каждом вызове метода.
 */
public class BotChat {
    public String getPhrase(String question) {
        File inputFile = new File(BotChat.class.getClassLoader().getResource("socket.txt").getPath());
        String stringFromFile;
        String he = "Не понял, может спросишь другое?"; //строка для ответов для бота.
        String you; //строка для задаваемых вопросов.
        String[] dividedLine = new String[4];  //Хранилище для подстрок.
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
            while (true) {
                try {
                    stringFromFile = reader.readLine();
                    if (stringFromFile != null) {
                        dividedLine = stringFromFile.split("/");
                        you = dividedLine[0];
                        he = dividedLine[1];
                        if (question.equals(you)) {
                            System.out.println(he);
                            break;
                        }
                    } else {
                        System.out.println(he = "Не понял, может спросишь другое?");
                        break;
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return he;
    }
}