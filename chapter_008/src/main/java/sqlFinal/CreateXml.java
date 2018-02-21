package sqlFinal;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
/*
Class for create XML file from table SQLite.
 */
public class CreateXml {


    private Connection con;
    private HashMap<String, String> map;
    private String saveFileName;

    public CreateXml(Connection con, HashMap<String, String> map) {
        this.con = con;
        this.map = map;
        saveFileName = new String(map.get("pathFile") + "1.xml");
    }

    public void start() {

        long time = System.currentTimeMillis();

        WriteSQLCopy writeSQLCopy = new WriteSQLCopy(con, map);

        writeSQLCopy.setOpenTable();
        writeSQLCopy.setCreateTable();
        writeSQLCopy.setInsertTable();

        System.out.println(String.format(" %s seconds for create DB file and fill table TEST",(System.currentTimeMillis() - time) / 1000));

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

        System.out.println(String.format(" Create file -- %s --  time spend is  %s seconds", saveFileName, (System.currentTimeMillis() - time) / 1000));
    }
}