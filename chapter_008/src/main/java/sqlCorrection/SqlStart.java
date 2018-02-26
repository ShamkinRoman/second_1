package sqlCorrection;

import java.io.*;
import java.util.Scanner;

public class SqlStart {
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        boolean flag = false;
        try {
            if (args.length > 0) {
                flag = true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You do not point arguments. ---> Вы не указали параметры для запуска.");
            e.printStackTrace();
        }

        ArgsValid argsValid = new ArgsValid();
        argsValid.setDefaultArgs();
        if (flag) {
            argsValid.setArgs(args);
            argsValid.start();
        }
        String database = null;

        if (new File(argsValid.getDefaultPath()).exists()) {
            File conf = new File(argsValid.getDefaultPath());
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
            database = argsValid.getDefaultPath();
        }

        argsValid.setDefaultPath(database);
        argsValid.printPath();

        String dataB = "jdbc:sqlite:" + database + "newData.db";
        String saveFile = argsValid.getDefaultPath() + "1.xml";
        String convertFile = argsValid.getDefaultPath() + "2.xml";
        System.out.println(new File(argsValid.getDefaultPath() + "newData.db").exists() ? "File DB is exixst, connect to file " : "File NOT exists and I create file DB");
        System.out.println(String.format("DriverManager = %s", dataB));

        WriteSQLCopy writeSQLCopy = new WriteSQLCopy();
        writeSQLCopy.setNumer(Integer.parseInt(argsValid.getDefaultNumber()));
        writeSQLCopy.setCon(dataB);
        writeSQLCopy.start();

        CreateXml createXml = new CreateXml();
        createXml.setSaveFileName(saveFile);
        createXml.setCon(dataB);
        createXml.start();

        ConvertXML convertXML = new ConvertXML();
        convertXML.setConvertFileName(convertFile);
        convertXML.setFromFile(saveFile);
        convertXML.start();

        CalcSummaXML calcSummaXML = new CalcSummaXML();
        calcSummaXML.setFromFile(convertFile);
        calcSummaXML.start();

        System.out.println(String.format("Time execute program is %s seconds.", (System.currentTimeMillis() - time) / 1000));
    }
}
