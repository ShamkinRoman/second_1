package sqlCorrection;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/*
Class for calculating values in XML file
 */
public class CalcSummaXML {

    private String fromFile;

    public void setFromFile(String fromFile) {
        this.fromFile = fromFile;
    }

    public void start() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser;
        HandlerCalc handler = new HandlerCalc();
        {
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
        }
        System.out.println("===============================");
        System.out.println(String.format(" Summa elements is %s ", handler.getSumma()));
        System.out.println("===============================");
    }
}
