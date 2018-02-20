package sqlFinal;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class ConvertXML {

    private HashMap<String, String> map;
    private final String convertFileName;

    public ConvertXML(HashMap<String, String> map) {
        this.map = map;
        convertFileName = map.get("pathFile") + "2.xml";
    }

    public void start() {

        long time = System.currentTimeMillis();
        map.put("convertFileName", convertFileName);


        ParseSax parse = new ParseSax(map);
        ArrayList<String> list = parse.calc();

        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            XMLStreamWriter writer = factory.createXMLStreamWriter(new FileOutputStream(convertFileName));


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
        System.out.println(String.format("%s было потрачено на конвертирование.", (System.currentTimeMillis() - time) / 1000));

    }


}
