package inputOut.arhiveProject;

import picocli.CommandLine;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * Пример использования класса ZipOutputStream
 */
public class Archive {
    /*Объект класса File хранит ссылку на файл или папку, которую мы хотим
                                                                   заархивировать*/
    private static File srcObject;

    /*Объект класса File хранит ссылку на zip файл, вкоторый мы хотим архивировать*/
    private static File destFile;

    /*Вспомогательная переменная для удаления из полного пути всех файлов при
                                                                   архивировании*/
    private static String prefixObject;
    /* Список файлов которые не должны попасть в архив*/
    private static List<String> blackListFiles;

    /**
     * Вспомогательная функция для копирования байтов из одного потока в другой
     *
     * @param in  поток ввода
     * @param out поток вывода
     */
    private static void write(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        while (in.read(buffer) != -1) {
            out.write(buffer);
        }
    }

    /**
     * Вспомогательная функция проверки на пустоту директории
     *
     * @param directory директория
     * @return true если папка пуста, и false в противном случае
     */
    private static boolean isEmptyDirectory(File directory) {
        return directory.list().length == 0;
    }

    /**
     * @param fileName имя файла которое нужно проверить.
     * @return true если имя файла есть в списке, иначе false.
     */
    private static boolean isFileInBlackList(String fileName) {
        boolean result = false;
        for (String checkFileName : blackListFiles) {
            if (checkFileName.equals(fileName)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Функция для добавления в архив файла
     *
     * @param src       файл, который хотим заархивировать
     * @param out       поток для добавления в архив записи
     * @param entryName имя записи в архиве
     */
    private static void toArchiveTheFile(File src, ZipOutputStream out,
                                         String entryName) throws IOException {
        /*проверяем есть ли файл в черном списке.*/
        if (!isFileInBlackList(src.getName())) {
            /*Добавляем новую запись в архиве*/
            out.putNextEntry(new ZipEntry(entryName));
            /*Копируем содержимое файла в поток архива*/
            try (InputStream in = new FileInputStream(src)) {
                write(in, out);
            }
            /*Закрываем запись в архиве*/
            out.closeEntry();
            System.out.println("name: " + entryName+ "------ OK");
        }
    }

    /**
     * Функция для архивирования папки со всей ее иерархией
     *
     * @param src ссылка на директорию, которую хотим заархивировать
     * @param out поток для архивации
     */
    private static void arhive(File src, ZipOutputStream out) throws IOException {
        /*Просматриваем каждый файл и папку в заданной папке*/
        for (File object : src.listFiles()) {
            /*Отбрасываем ненужные папки в пути для задания имени записи в архиве*/
            String entryName = object.getPath().substring(prefixObject.length() + 1);
            /*Если объект это папка*/
            if (object.isDirectory()) {
                /*либо она пустая либо нет*/
                if (isEmptyDirectory(object)) {
                    /*Если пустая, то нужно просто создать запись в архиве*/
                    out.putNextEntry(new ZipEntry(entryName + "/"));
                    out.closeEntry();
                    System.out.println("name: " + entryName+ "------ OK");
                } else {
                    /*если папка не пустая, то мы вызываемся рекурсивно на этой папке*/
                    arhive(object, out);
                }
            } else {
                /*
                  Если рассматриваемый объект это файл, то нужно его заархивировать.
                  Для этого мы используем функцию toArchiveTheFile
                */
                toArchiveTheFile(object, out, entryName);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        /*Проверяем аргументы командной строки*/
        ParseCommandLine parseArgs = new ParseCommandLine(); // используем FrameWork для парсинга коммандной строки.
        new CommandLine(parseArgs).parse(args); // парсим коммандную строку
        srcObject = new File(parseArgs.directory);
        destFile = new File(parseArgs.archive);
        blackListFiles = Arrays.asList(parseArgs.blackFiles);
        System.out.println("start archiving...");
        /*отбрасываем все папки в пути до корня от нашего рассматриваемого объекта*/
        prefixObject = srcObject.getParent();
        /*Создаем поток для архивирования*/
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(destFile));
        /*Если мы захотели заархивироваться один файл*/
        if (srcObject.isFile()) {
            /*то просто его заархивируем*/
            String fileName = srcObject
                    .getPath()
                    .substring(prefixObject.length() + 1);
            toArchiveTheFile(srcObject, out, fileName);
            System.out.println(" ------ OK");
        } else if (isEmptyDirectory(srcObject)) {
            /*Если мы хотим заархивировать пустую папку*/
            /*То просто создадим запись в архиве*/
            out.putNextEntry(new ZipEntry(srcObject.getName() + "/"));
            out.closeEntry();
        } else {
                /*Если мы хотим заархивировать не пустую папку,
                              то воспользуемся функцией archive для архивации папки*/
            arhive(srcObject, out);
        }
        out.close();
        System.out.println("end archiving.");
    }
}