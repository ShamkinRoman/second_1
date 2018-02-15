package sql2;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class CreateXml {
    public static void main(String[] args) {

        long time =System.currentTimeMillis();

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:d://sqlite//newData.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        WriteSQLCopy writeSQLCopy = new WriteSQLCopy(connection);

        writeSQLCopy.setOpenTable();
        writeSQLCopy.setCreateTable();
        writeSQLCopy.setInsertTable();

        Statement rezult = null;


            try {

                rezult = connection.createStatement();


                Element company = new Element("entries");
                Document doc = new Document(company);
                doc.setRootElement(company);


//                ResultSet rez = rezult.executeQuery("select numer from test as t where t.id between 1100001 and 1600000");
                ResultSet rez = rezult.executeQuery("select numer from test");

                while(rez.next()) {


                    try {




                           Element staff02 = new Element("entry");

                            staff02.addContent(new Element("field").setText(Integer.toString(rez.getInt(1))));

                            doc.getRootElement().addContent(staff02);

                        }  catch (RuntimeException e) {

                    }

                }

                XMLOutputter xmlOutput = new XMLOutputter();
                try {
                    xmlOutput.output(doc, new FileWriter("d:\\TEST03.xml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                System.out.println("Таблица тест не найдена");
            }
       writeSQLCopy.setCloseTable();

        System.out.println((System.currentTimeMillis()-time)/1000);
    }
}