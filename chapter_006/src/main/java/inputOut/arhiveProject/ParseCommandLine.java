package inputOut.arhiveProject;

import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


public class ParseCommandLine {
    @Option(names = {"-d", "--directory"}, paramLabel = "directory", description = "directory")
    public String directory = System.getProperty("java.io.tmpdir");

    @Option(names = {"-e", "--exclude"}, paramLabel = "exclude", description = "exclude")
    public boolean exclude = false;
    @Parameters(arity = "0..*", paramLabel = "FILE", description = "File(s) to exclude")
    public String[] blackFiles = new String[]{"",""};

    @Option(names = {"-o", "--output"}, paramLabel = "ARCHIVE", description = "the archive file")
    public String archive = "pack.zip";

}