package ru.job4j.findtext;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@ThreadSafe
public class ParallelSearch {

    private String pathSearch; // Путь поиска.
    private String textSearch; // Текст который надо найти.
    private List<String> exts; // Расширения файлов, в которых необходимо искать.

    @GuardedBy("this")
    private ArrayList<File> listFiles = new ArrayList<>(); //Хранилище для файлов с заданным расширениями.
    @GuardedBy("this")
    private ArrayList<File> listTxtFound = new ArrayList<>(); // Хранилище для файлов в которых найден текст.

    //Геттер пути поиска.
    public String getPathSearch() {
        return pathSearch;
    }

    //Геттер найденых файлов.
    public ArrayList<File> getListFiles() {
        return listFiles;
    }

    //Конструктор.
    public ParallelSearch(String pathSearch, String textSearch, List<String> exts) {
        this.pathSearch = pathSearch;
        this.textSearch = textSearch;
        this.exts = exts;
    }

    /**
     * Метод для поиска файлов с заданным расширением.
     *
     * @param pathSearch путь поиска.
     */
    public synchronized void listFiles(String pathSearch) {

        for (File value : (new File(pathSearch)).listFiles()) {
            if (value.isFile()) {
                for (String extsFiles : exts) {
                    if (value.getName().endsWith(extsFiles)) {
                        listFiles.add(value);
                    }
                }
            } else {
                if (value.isDirectory()) {
                    listFiles(value.getPath());
                }
            }
        }
    }

    /**
     * Метод для поска текста в файлах.
     *
     * @param file файл.
     */
    public synchronized void findTextInFile(File file) {

        try {
            FileReader fileReader = new FileReader(file);
            Scanner scanner = new Scanner(fileReader);

            while (scanner.hasNextLine()) {
                if (scanner.nextLine().contains(textSearch)) {
                    listTxtFound.add(file);
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Результат поиска текста.
    public List<String> result() {
        List<String> result = new ArrayList<>();

        for (File value : listTxtFound) {
            result.add(value.getAbsolutePath());
        }

        return result;
    }

    /**
     * Майн !!!
     *
     * @param args аргументы.
     */
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add(".txt"); // задаем расширения.
        list.add(".cfg");

        String path = "C:\\Program Files (x86)"; //Путь поиска.
        String text = "f"; // Текст для поиска.

        ParallelSearch parallelSearch = new ParallelSearch(path, text, list);

        Thread addText = new Thread(new AddTxt(parallelSearch)); //Поток для поиска файлов.
        addText.start();
        try {
            addText.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //Потоки для поиска текста в файлах.
        //Для каждого файла свой поток.
        Thread[] finds = new Thread[parallelSearch.getListFiles().size()];
        int i = 0;
        for (File value : parallelSearch.getListFiles()) {
            finds[i] = new Thread(new FindTextInFile(parallelSearch, value));
            finds[i++].start();
        }
        i = 0;
        for (File value : parallelSearch.getListFiles()) {
            try {
                finds[i++].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Вывод результатов на консоль.
        Print print = new Print(parallelSearch);
        print.print();

    }


    //    ===============================================================================
//    PRIVATE CLASSS AddTxt
    private static class AddTxt implements Runnable {

        private ParallelSearch test;

        public AddTxt(ParallelSearch test) {
            this.test = test;
        }


        public void add() {
            test.listFiles(test.getPathSearch());
        }

        @Override
        public void run() {
            add();
        }
    }

//    ===============================================================================
//    PRIVATE CLASSS FindTextInFile

    private static class FindTextInFile implements Runnable {

        private ParallelSearch test;
        private File file;

        public FindTextInFile(ParallelSearch test, File file) {
            this.test = test;
            this.file = file;
        }

        public void find() {
            test.findTextInFile(file);
        }

        @Override
        public void run() {
            find();
        }
    }

    //    ===============================================================================
//    PRIVATE CLASSS PRINT
    private static class Print {
        private ParallelSearch test;

        public Print(ParallelSearch test) {
            this.test = test;
        }

        public void print() {
            if (test.result().isEmpty()) {
                System.out.println("Ничего не найдено.");
            } else {
                System.out.println("Совпадения найдены  в следующих файлах: ");
                for (String value : test.result()) {
                    System.out.println(value);
                }
            }
        }
    }
}

