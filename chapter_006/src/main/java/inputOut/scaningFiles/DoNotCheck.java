package inputOut.scaningFiles;

import java.nio.file.FileVisitOption;
import java.util.EnumSet;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.*;

//Используем класс SimpleFileVisitor, который реализовывает методы интерфейса FileVisitor<br/>
public class DoNotCheck extends SimpleFileVisitor<Path> {
    //Выводим информацию о обрабатываемом в данный момент файле.
// метод Files.probeContentType выводит информацию о типе контента
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) throws IOException {
        if (attr.isSymbolicLink()) {
            System.out.format("Symbolic link: %s ", file);
        } else if (attr.isRegularFile()) {
            System.out.format("Regular file: %s Content is %s%n ", file, Files.probeContentType(file));
        } else {
            System.out.format("Other: %s ", file);
        }
        System.out.println("(" + attr.size() + "bytes)");
        return CONTINUE;
    }

    //Выводим информацию о посещенном каталоге<br/>
    @Override
/* Перечисление FileVisitResult имеет следующие варианты<br/>
CONTINUE продолжить проход.<br/>
SKIP_SIBLINGS продолжить проход без осмотра дочерних папок.<br/>
SKIP_SUBTREE продолжить без просмотра объектов данной папки.<br/>
TERMINATE заверщить.<br/>
*/
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        System.out.format("Directory: %s%n", dir);
        return CONTINUE;
    }

    //в случае ошибки доступа к файлу выбрасывается исключение IOException<br/>
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        System.err.println(exc);
        return CONTINUE;
    }

    public static void main(String[] args) throws IOException {
        //создаем объект Path
        Path startingDir = Paths.get(System.getProperty("java.io.tmpdir"));
        //создаем экземпляр нашего класса, реализующего FileVisit<br/>
        DoNotCheck pf = new DoNotCheck();
        //создаем экземпляр EnumSet, необходимый нам как параметр, и указывающий,
// что программа при  прохождении дерева файлов, следует по ссылкам
        EnumSet<FileVisitOption> options = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
        int maxDepth = 20; //максимальное число уровней каталога для просмотра
        /* Запуск анализа дерева файлов. Используется один из методов класса Files*/
        Files.walkFileTree(startingDir, options, maxDepth, pf);
    }
}