package sqlFinal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

public class StartFile {

    public static void main(String[] args) {

        boolean flag = false;

        try {
            if (args.length > 0) {
                flag = true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        HashMap<String, String> map;

        if (flag) {
            ArgsValid argsValid = new ArgsValid(args);
            map = argsValid.getMap();
        } else {
            ArgsValid argsValid = new ArgsValid();
            map = argsValid.getMap();
        }

        String database = null;
        if (new File(map.get("pathFile")).exists()) {
            File conf = new File(map.get("pathFile"));

            FileReader fileReader = null;
            try {
                fileReader = new FileReader(conf);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Scanner scanner = new Scanner(fileReader);

            while (scanner.hasNextLine()) {
                database = scanner.nextLine();
                break;
            }
            scanner.close();

            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String dataB = "jdbc:sqlite:" + database+"newData.db";
//        String dataB="jdbc:sqlite:d://sqlite//newData.db";

        System.out.println(dataB);

        try {
            Connection con = DriverManager.getConnection(dataB);
            System.out.println("Ectb connect");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        CreateXml createXml = new CreateXml(dataB);


        createXml.start();
    }

}
