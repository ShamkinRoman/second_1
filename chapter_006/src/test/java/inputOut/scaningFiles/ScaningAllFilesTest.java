package inputOut.scaningFiles;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ScaningAllFilesTest {
//    private ScaningAllFiles scan = new ScaningAllFiles();
//    private List<String> exts;
//    private String path;
//    private File[] createFiles;
//    private File[] directory;
//    private File[] expect;
//
//    @Before
//    public void setUp() throws IOException {
//        path = System.getProperty("java.io.tmpdir");
//        exts = new ArrayList<>();
//        exts.add("txt");
//        exts.add("cfg");
//        exts.add("java");
//        directory = new File[]{new File(path + "folderTest/test1/"),
//                new File(path + "folderTest/test2/one/two/"),
//                new File(path + "folderTest/test3/one/two/"),
//                new File(path + "folderTest/test4/one/"),
//                new File(path + "folderTest/test5/one/two/"),
//                new File(path + "folderTest/test6/one/two/three/")};
//        createFiles = new File[]{new File(path + "folderTest/test1/Aone.txt"),
//                new File(path + "folderTest/test1/one.java"),
//                new File(path + "folderTest/test2/one/two/one.cfg"),
//                new File(path + "folderTest/test3/one/two/one.java"),
//                new File(path + "folderTest/test4/one/one.exe"),
//                new File(path + "folderTest/test5/one/two/one.flop"),
//                new File(path + "folderTest/test6/one/two/three/one.asw"),
//                new File(path + "folderTest/test6/one/two/three/two.txt")};
//        expect = new File[]{new File(path + "folderTest/test1/Aone.txt"),
//                new File(path + "folderTest/test1/one.java"),
//                new File(path + "folderTest/test2/one/two/one.cfg"),
//                new File(path + "folderTest/test3/one/two/one.java"),
//                new File(path + "folderTest/test6/one/two/three/two.txt")};
//        FileUtils.deleteDirectory(new File(path + "folderTest")); //Удаляем папку для создания файлов.
//        // Если изменять условия, то могут остаться старые файлы, которые мешают прохождению теста.
//        for (File folder : directory) {
//            if (!folder.exists()) {
//                folder.mkdirs();
//            }
//        }
//        for (File file : createFiles) {
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//        }
//    }
//
//    @Test
//    public void listFiles() {
//        try {
//            List<File> scanList = scan.listFiles(path + "folderTest/", exts);
//            List<File> filesExpect = Arrays.asList(expect).stream().sorted().collect(Collectors.toList()); //сортируем иначе тест не проходит.
//            assertArrayEquals(filesExpect.toArray(), scanList.toArray());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}