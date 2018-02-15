package sql3Parse;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class convertXML {

    public static void main(String[] args) {

        long time = System.currentTimeMillis();
        parseSax parse = new parseSax();
        ArrayList<String> list = parse.calc();

        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            XMLStreamWriter writer = factory.createXMLStreamWriter(new FileOutputStream("D://TEST099.xml"));

            writer.writeStartDocument();

            writer.writeStartElement("entries");
            for (String value : list) {
                writer.writeStartElement("entry");
                writer.writeAttribute("field", value);
                writer.writeEndElement();
            }

            writer.writeEndElement();
            writer.writeEndDocument();


        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("%s было потрачено на конвертирование.",(System.currentTimeMillis() - time) / 1000));

    }


}
