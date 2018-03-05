package sql;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class PathUrl {
    public void print() {

        URL urlToConfigFile = this.getClass().getClassLoader().getResource("config.properties");
        System.out.println(urlToConfigFile);
        String path = String.valueOf(urlToConfigFile);

        System.out.println(path);
        String fileProperties = "";

        Properties properties = new Properties();
        FileInputStream fileInputStream;


        try {
            if (urlToConfigFile != null) {
                fileProperties = urlToConfigFile.getFile();
            }
            properties.load(new FileInputStream(fileProperties));
        } catch (IOException e) {
            System.out.println(String.format("Error wasn't loaded with properties like {}", properties, e));
        }
        String site = properties.getProperty("site");
        String loginToSite = properties.getProperty("user");
        String passwordToSite = properties.getProperty("password");

        System.out.println(
                "site: " + site
                        + "\nloginToSite: " + loginToSite
                        + "\npasswordToSite: " + passwordToSite
        );

    }
}
