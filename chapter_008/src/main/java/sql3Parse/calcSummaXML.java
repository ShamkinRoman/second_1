package sql3Parse;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class calcSummaXML {
    public static void main(String[] args) {


        long time = System.currentTimeMillis();

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        SAXParser saxParser;

        HandlerCalc handler = new HandlerCalc();

        {
            try {
                saxParser = saxParserFactory.newSAXParser();
                try {
                    saxParser.parse("D://TEST099.xml", handler);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
        }

        System.out.println(handler.getSumma());
        System.out.println(String.format("%s было потрачено на вычисление суммы.", (System.currentTimeMillis() - time) / 1000));
    }
}
