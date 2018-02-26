package sqlCorrection;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;

/*
Class for Parsing XML file.
 */
public class ParseSax {

    private String fromFile;

    public void setFromFile(String fromFile) {
        this.fromFile = fromFile;
    }

    public ArrayList<String> calc() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser;
        Handler handler = new Handler();
        try {
            saxParser = saxParserFactory.newSAXParser();
            try {
                saxParser.parse(fromFile, handler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("cardinality = %s", handler.getData().size()));
        return handler.getData();
    }
}