package ru.job4j.FindFile;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Toto {


    private static ArrayList<File> listTxt = new ArrayList<>();
    private static ArrayList<File> list = new ArrayList<>();
    private static HashMap<File, File> folders = new HashMap<>();
    private static int dirSize = 0;
    private static int fileSize = 0;


    public static void main(String[] args) {
        File file = new File("//home//dispe//aaa");

        System.out.println(file.isDirectory());

        File files2 = new File("//home//dispe//aaa//Новый");

        for (File value : files2.listFiles()) {
            System.out.println(value.getName());
        }
        printFile(file);


//        for (File value : list) {
//            System.out.println(String.format(value.getAbsolutePath()));
//        }
//
//        System.out.println(String.format("%s из них %s каталогов и %s файлов", list.size(), dirSize, fileSize));
//
//        for (File value : listTxt) {
//            System.out.println(String.format("%s ", value.getAbsolutePath()));
//        }


        Result result = new Result();
        Thread[] threads = new Thread[listTxt.size()];
        String findTxt="Adobe Systems";

        for (int i = 0; i < listTxt.size(); i++) {
            threads[i] = new Thread(new Read(listTxt.get(i), findTxt, result));
        }

        for (Thread value : threads) {
            value.start();
        }

        for (Thread value : threads) {
            try {
                value.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        if (result.getResult().isEmpty()) {
            System.out.println("Не нейдено");
        } else {
            for (String value : result.getResult()) {
                System.out.println(String.format("%s найдено в следующих файлах %s",findTxt, value));
            }
        }


    }


    public static void printFile(File file) {

        String stroka;
        for (File value : file.listFiles()) {
            if (value.isFile()) {
                list.add(value);
                stroka = value.getName();
                if (stroka.endsWith(".txt")) {
                    listTxt.add(value);
                }
                fileSize++;
            } else {
                if (value.isDirectory()) {
                    list.add(value);
                    dirSize++;
                    printFile(value);
                }
            }
        }

    }


    private static class Read implements Runnable {

        private Result result;

        private File file;
        private String txt;

        public Read(File file, String txt, Result result) {
            this.file = file;
            this.txt = txt;
            this.result = result;
        }

        public void findTxt() throws FileNotFoundException {

            String stroka;

            FileReader fileReader = new FileReader(file);
            Scanner scanner = new Scanner(fileReader);

            while (scanner.hasNext()) {
                stroka = scanner.next();
                if (stroka.contains(txt)) {
                    result.addResult(file.getAbsolutePath());
                    break;
                }

            }
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        @Override
        public void run() {

            try {
                findTxt();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    private static class Result {

        private ArrayList<String> result = new ArrayList<>();

        public ArrayList<String> getResult() {
            return result;
        }

        public synchronized void addResult(String text) {
            this.result.add(text);
        }

    }
}
