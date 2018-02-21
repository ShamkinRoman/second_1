package sqlFinal;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
/*
Class for Parsing XML file.
 */
public class ParseSax {

    private HashMap<String, String> map;

    public ParseSax(HashMap<String, String> map) {
        this.map = map;
    }

    public ArrayList<String> calc() {

        long time = System.currentTimeMillis();

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        SAXParser saxParser;

        Handler handler = new Handler();


        try {
            saxParser = saxParserFactory.newSAXParser();
            try {
                saxParser.parse(map.get("saveFileName"), handler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }


        System.out.println(String.format("number = %s", handler.getData().size()));

        System.out.println(String.format("%s second for parsing.", (System.currentTimeMillis() - time) / 1000));

        return handler.getData();
    }

}