package sqlFinal;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ArgsValid {

    private HashMap<String, String> map = new HashMap<>(2);

    private String[] args;


    public ArgsValid() {
        map.put("pathFile", "");
        map.put("numer", "500000");
        System.out.println("You can not indetifity congig.cfg and N");
        System.out.println(String.format("I set database path is %s  and N = %s",new File(map.get("pathFile")).getAbsoluteFile(),map.get("numer")  ));
    }

    public ArgsValid(String[] args) {
        this.args = args;
        start();
    }

    private void start() {
        checkPathFile();
        checkNumer();

    }

    private void checkPathFile() {
        for (String value : args) {
            if (value.equals("config.cfg")) {
                map.put("pathFile", value);
                break;
            }
        }
        if (map.get("pathFile")==null) {
            map.put("pathFile", "");
            System.out.println(String.format("I can not found config.cfg , and I set path database is %s", new File("").getAbsoluteFile()));
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

        if (map.get("numer")==null) {
            map.put("numer", "500000");
            System.out.println("You can not identifity N, and I set N = 500_000");
        }

    }

    public void setStart() {
        start();
    }

    public HashMap<String,String> getMap(){
        return this.map;
    }
}
