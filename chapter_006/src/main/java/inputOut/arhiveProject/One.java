package inputOut.arhiveProject;

import picocli.CommandLine;

import java.io.File;

public class One {

    public static void main(String[] args) {
        String[] args1 = {"-o", "pack.zip", "-d", "DIRECTORY", "-e","inputFile2", "inputFile3"};
        Example tar = new Example();
        new CommandLine(tar).parse(args1);
        System.out.println(tar.directory.getName());
        System.out.println(tar.archive.getName());
        if (tar.exclude) {
            for (File file : tar.inputFiles) {
                System.out.println(file.getName());
            }
        }        System.out.println(tar.exclude);
    }
}
