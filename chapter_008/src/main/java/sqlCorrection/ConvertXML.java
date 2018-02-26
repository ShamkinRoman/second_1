package sqlCorrection;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/*
Class for convert XML file in another XML file.
 */
public class ConvertXML {
    private String convertFileName;
    private String fromFile;

    public void setFromFile(String fromFile) {
        this.fromFile = fromFile;
    }

    public void setConvertFileName(String convertFileName) {
        this.convertFileName = convertFileName;
    }

    public void start() {
        ParseSax parse = new ParseSax();
        parse.setFromFile(fromFile);
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
            writer.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
