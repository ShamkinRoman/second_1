package inputOut.scaningFiles;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScaningAllFiles {

    private ArrayList<File> listFiles = new ArrayList<>(); //Хранилище для файлов с заданным расширениями.

    /**
     * Метод для поиска файлов с заданным расширением.
     *
     * @param pathSearch путь поиска.
     */
    public List<File> listFiles(String pathSearch, List<String> exts) throws IOException {
        for (File value : (new File(pathSearch)).listFiles()) {
            if (value.isFile()) {
                for (String extsFiles : exts) {
                    if (value.getName().endsWith(extsFiles)) {
                        listFiles.add(value);
                    }
                }
            } else {
                if (value.isDirectory()) {
                    listFiles(value.getPath(), exts);
                }
            }
        }
        return listFiles;
    }
}