package sqlFinal;

import java.io.File;
import java.util.HashMap;

/*
Class for validation arguments in start program.
 */

public class ArgsValid {

    private HashMap<String, String> map;
    private final String defaultNumber = "20";
    private final String defaultPath = "";
    private final String configFileName = "config.cfg";

    private String[] args;


    public ArgsValid(HashMap<String, String> map) {
        this.map = map;
        map.put("pathFile", defaultPath);
        map.put("numer", defaultNumber);
        System.out.println("You can not indetifity congig.cfg and N. --> Не указан файл config.sys и N");
        System.out.println(String.format("I set database path is %s  and N = %s   -->  Выбран путь до БД %s  и N = %s", new File(map.get("pathFile")).getAbsoluteFile(), map.get("numer"), new File(map.get("pathFile")).getAbsoluteFile(), map.get("numer")));
    }

    public ArgsValid(String[] args, HashMap<String, String> map) {
        this.map = map;
        this.args = args;
        start();
    }

    private void start() {
        checkPathFile();
        checkNumer();
    }

    private void checkPathFile() {

        for (String value : args) {
            if (value.equals(configFileName)) {
                map.put("pathFile", value);
                break;
            }
        }
        if (map.get("pathFile") == null) {
            map.put("pathFile", defaultPath);
            System.out.println(String.format("I can not found config.cfg , and I set path database is %s -->  Не могу могу найти config.sys, выбран следующий путь до БД %s", new File("").getAbsoluteFile(), new File("").getAbsoluteFile()));
        }
    }

    private void checkNumer() {

        for (String value : args) {
            try {
                Long.parseLong(value);
                map.put("numer", value);
            } catch (NumberFormatException e) {

            }
        }

        if (map.get("numer") == null) {
            map.put("numer", defaultNumber);
            System.out.println(String.format("You can not identifity N, and I set N = %s.   --> Не указано N, выбрано N = %s", map.get("numer"), map.get("numer")));
        }
    }

    public void setStart() {
        start();
    }

    public HashMap<String, String> getMap() {
        return this.map;
    }
}
