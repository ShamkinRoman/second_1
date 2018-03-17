package parserSQL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

/*
 * Class for storage data in Map.
 */
public class Storage {
    private HashMap<String, String> map = new HashMap();
    private static final Logger log = LoggerFactory.getLogger(CheckConnectToDataBase.class.getName());

    public Storage() {
        fill();
    }

    /*
     * fill Storage by data.
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
            log.warn(String.format("Error wasn't loaded with properties like {}", properties, e));
        }
        map.put("user", properties.getProperty("user"));
        map.put("password", properties.getProperty("password"));
        map.put("pathDB", properties.getProperty("pathDB"));
        map.put("fullPath", properties.getProperty("fullPath"));
        map.put("dbName", properties.getProperty("dbName"));
        map.put("tableName", properties.getProperty("tableName"));
        map.put("startupFrequency", properties.getProperty("startupFrequency"));
    }

    public String getValue(String value) {
        return map.get(value);
    }
}
