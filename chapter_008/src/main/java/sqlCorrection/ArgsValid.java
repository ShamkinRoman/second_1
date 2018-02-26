package sqlCorrection;

import java.io.File;

/*
Class for validation arguments in start program.
 */
public class ArgsValid {
    private String defaultNumber;
    private String defaultPath;
    private String configFileName;
    private String[] args;

    public void setArgs(String[] args) {
        this.args = args;
    }

    public void start() {
        checkPathFile();
        checkNumer();
    }

    private void checkPathFile() {
        for (String value : args) {
            if (value.equals(configFileName)) {
                defaultPath = value;
                break;
            }
        }
    }

    private void checkNumer() {
        for (String value : args) {
            try {
                Integer.parseInt(value);
                defaultNumber = value;
            } catch (NumberFormatException e) {
                System.out.println("Check next value in command line");
            }
        }
    }

    public void setDefaultArgs() {
        defaultNumber = "-1";
        defaultPath = "";
        configFileName = "config.cfg";
    }

    public void printPath() {
        if (defaultPath == null) {
            defaultPath = "";
        }
        if (defaultPath == "") {
            System.out.println(String.format("I can not found or incorrently config.cfg , and I set path database is %s -->  Не могу могу найти или некорректный config.cfg, выбран следующий путь до БД %s", new File("").getAbsoluteFile(), new File("").getAbsoluteFile()));
        } else {
            System.out.println(String.format("DataBase path is %s", defaultPath));
        }
        if (Long.parseLong(defaultNumber) < 0) {
            defaultNumber = "20";
            System.out.println(String.format("You can not identifity N, and I set N = %s.   --> Не указано N, выбрано N = %s", defaultNumber, defaultNumber));
        } else {
            System.out.println(String.format("N = %s", defaultNumber));
        }
    }

    public String getDefaultNumber() {
        return defaultNumber;
    }

    public String getDefaultPath() {
        return defaultPath;
    }

    public void setDefaultPath(String defaultPath) {
        this.defaultPath = defaultPath;
    }
}
