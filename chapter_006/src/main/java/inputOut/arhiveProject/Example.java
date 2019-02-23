package inputOut.arhiveProject;

import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;

public class Example {
    @Option(names = {"-d", "--directory"}, paramLabel = "directory", description = "directory")
    public File directory;

    @Option(names = {"-e", "--exclude"}, paramLabel = "exclude", description = "exclude")
    public boolean exclude = false;
    @Parameters(arity = "1..*", paramLabel = "FILE", description = "File(s) to exclude")
    public File[] inputFiles;

    @Option(names = {"-o", "--output"}, paramLabel = "ARCHIVE", description = "the archive file")
    public File archive;

}