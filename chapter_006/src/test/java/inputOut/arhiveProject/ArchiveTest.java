package inputOut.arhiveProject;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ArchiveTest {
//    private String path;
//    private File[] createFiles;
//    private File[] directory;
//
//    @Before
//    public void setUp() throws IOException {
//        path = System.getProperty("java.io.tmpdir") + "folderTest";
//        directory = new File[]{new File(path + "/test1/")};
//        createFiles = new File[]{new File(path + "/test1/one.txt"), new File(path + "/test1/two.txt")};
//
//        FileUtils.deleteDirectory(new File(path)); //Удаляем папку для создания файлов.
//        // Если изменять условия, то могут остаться старые файлы, которые мешают прохождению теста.
//        for (File folder : directory) {
//            if (!folder.exists()) {
//                folder.mkdirs();
//            }
//        }
//        if (!new File(path + "1").exists()) {
//            new File(path + "1").mkdirs();
//        }
//        for (File file : createFiles) {
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//        }
//    }
//
//    @Test
//    public void whenFolderExistThenTrue() {
//        String[] args = new String[]{"-d", path, "-o", path + "1" + "\\test.zip", "-e", "one.java"};
//
//        try {
//            Archive.main(args);
//            assertTrue(new File(path + "1" + "\\test.zip").exists());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Test
//    public void whenOneFileInBlackList() {
//        //Добавиться всего один файл из двух.
//        String[] args = new String[]{"-d", path, "-o", path + "1" + "\\test2.zip", "-e", "one.txt"};
//        List<String> check = new ArrayList<>();
//        try {
//            Archive.main(args);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //Проверим что в архиве всего один файл.
//        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(path + "1" + "\\test2.zip"))) {
//            ZipEntry entry;
//            String name;
//            while ((entry = zin.getNextEntry()) != null) {
//                name = entry.getName(); // получим название файла
//                check.add(name);
//                zin.closeEntry();
//            }
//        } catch (Exception ex) {
//
//            System.out.println(ex.getMessage());
//        }
//        String expect = "folderTest\\test1\\two.txt";
//        assertThat(check.get(0), is(expect)); //имя совпало с ожидаемым.
//        assertThat(check.size(), is(1)); //всего один файл в коллекции.
//    }
}