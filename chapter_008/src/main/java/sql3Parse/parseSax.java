package sql3Parse;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;

public class parseSax {

    public ArrayList<String> calc() {

        long time = System.currentTimeMillis();

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        SAXParser saxParser;

        Handler handler = new Handler();

        {
            try {
                saxParser = saxParserFactory.newSAXParser();
                try {
                    saxParser.parse("D://TEST03.xml", handler);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
        }

        System.out.println(String.format("Количество элементов %s", handler.getData().size()));

        System.out.println(String.format("%s секунд было затрачено на парсинг.", (System.currentTimeMillis() - time) / 1000));

        return handler.getData();
    }

}