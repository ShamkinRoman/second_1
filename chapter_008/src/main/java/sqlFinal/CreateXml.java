package sqlFinal;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

public class CreateXml {


    private Connection con;
    private HashMap<String, String> map;
    private String saveFileName;

    public CreateXml(Connection con, HashMap<String, String> map) {
        this.con = con;
        this.map = map;
//        System.out.println(String.format("Путь равен %s", map.get("numer")));
        saveFileName = new String (map.get("pathFile") + "1.xml");
    }

    public void start() {

        long time = System.currentTimeMillis();

        Writesqlcopy writeSQLCopy = new Writesqlcopy(con, map);

        writeSQLCopy.setOpenTable();
        writeSQLCopy.setCreateTable();
        writeSQLCopy.setInsertTable();

        Statement rezult = null;


        try {

            rezult = con.createStatement();


            Element company = new Element("entries");
            Document doc = new Document(company);
            doc.setRootElement(company);

            ResultSet rez = rezult.executeQuery("select numer from test");

            while (rez.next()) {


                try {


                    Element staff02 = new Element("entry");

                    staff02.addContent(new Element("field").setText(Integer.toString(rez.getInt(1))));

                    doc.getRootElement().addContent(staff02);

                } catch (RuntimeException e) {

                }

            }

            XMLOutputter xmlOutput = new XMLOutputter();
            try {
                xmlOutput.output(doc, new FileWriter(saveFileName));
                map.put("saveFileName", saveFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Таблица тест не найдена");
        }
        writeSQLCopy.setCloseTable();

        System.out.println(String.format(" Time create -- %s -- file  is %s seconds", saveFileName, (System.currentTimeMillis() - time) / 1000));
    }
}