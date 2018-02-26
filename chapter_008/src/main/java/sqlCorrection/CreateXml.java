package sqlCorrection;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import java.io.*;
import java.sql.*;

/*
Class for create XML file from table SQLite.
 */
public class CreateXml {
    private String saveFileName;
    private Connection con;

    public void setSaveFileName(String saveFileName) {
        this.saveFileName = new String(saveFileName);
    }

    public void setCon(String connection) {
        try {
            this.con = DriverManager.getConnection(connection);
            System.out.println("Connect module CreateXML to DB successfully.");
        } catch (SQLException e) {
            System.out.println("Error to make connection on DB");
            PrintStream st = null;
            try {
                st = new PrintStream(new FileOutputStream("logCreateXML.txt"));
                System.out.println("Check file ==> logCreateXML.txt <== ");
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            System.setErr(st);
            System.setOut(st);
            e.printStackTrace();
            st.close();
        }
    }

    public void start() {
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
                    e.printStackTrace();
                }
            }
            XMLOutputter xmlOutput = new XMLOutputter();
            try {
                xmlOutput.output(doc, new FileWriter(saveFileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Таблица тест не найдена");
        }
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}