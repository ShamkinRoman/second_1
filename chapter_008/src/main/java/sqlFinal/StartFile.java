package sqlFinal;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

/*
Start program for TASK # 20459
 */
public class StartFile {


    public static void main(String[] args) {

        long time = System.currentTimeMillis();
        HashMap<String, String> map = new HashMap<>(2);


        boolean flag = false;

        try {
            if (args.length > 0) {
                flag = true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }


        if (flag) {
            new ArgsValid(args, map);

        } else {
            new ArgsValid(map);

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
        } else {
            database = map.get("pathFile");
        }

        map.put("pathFile", database);
        String dataB = "jdbc:sqlite:" + database + "newData.db";

        String answer = new File(map.get("pathFile") + "newData.db").exists() ? "File DB is exixst, connect to file " : "File NOT exists and I create file DB";


        System.out.println(answer);

        System.out.println(String.format(" DriverManager = %s", dataB));
        Connection con = null;

        try {
            con = DriverManager.getConnection(dataB);
            System.out.println("Connect to DB successfully. --> Удалось подключиться к БД ");
        } catch (SQLException e) {
            System.out.println("Error to make connection on DB");
            System.out.println("Check you system on SQLite. --> Не обнаружена SQLite, проверьте ее наличие.");
            System.out.println("Not detect folder for DB.  ---> Проверьте наличие директории указанной в config.sys");

            PrintStream st = null;
            try {
                st = new PrintStream(new FileOutputStream("log.txt"));
                System.out.println("Check file ==> log.txt <== ");
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }

            System.setErr(st);

            System.setOut(st);

            e.printStackTrace();
            st.close();


        }

        CreateXml createXml = new CreateXml(con, map);


        createXml.start();

        ConvertXML convertXML = new ConvertXML(map);

        convertXML.start();

        CalcSummaXML calc = new CalcSummaXML(map);
        calc.start();

        System.out.println(String.format(" Full time execute program is %s seconds", (System.currentTimeMillis() - time) / 1000));

    }

}
