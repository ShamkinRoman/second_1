package crudServlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

/**
 * This is class for storage settings to connect DataBase for ex.#103403.
 * And use in DBStore class.
 */
public class DataSetup {
    private HashMap<String, String> map = new HashMap();

    public DataSetup() {
        fill();
    }

    /*
     * fill Data by data.
     */
    public void fill() {
        URL urlToConfigFile = this.getClass().getClassLoader().getResource("sqlConfig.properties");
        String fileProperties = "";
        Properties properties = new Properties();
        FileInputStream fileInputStream;
        try {
            if (urlToConfigFile != null) {
                fileProperties = urlToConfigFile.getFile();
            }
            properties.load(new FileInputStream(fileProperties));
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put("user", properties.getProperty("user"));
        map.put("password", properties.getProperty("password"));
        map.put("fullPathJSP", properties.getProperty("fullPathJSP"));
        map.put("jspTable", properties.getProperty("jspTable"));
        map.put("passTable", properties.getProperty("passwordTable"));
    }

    public HashMap<String, String> getMap() {
        return map;
    }
}