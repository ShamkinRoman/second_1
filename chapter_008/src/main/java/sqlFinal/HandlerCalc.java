package sqlFinal;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HandlerCalc extends DefaultHandler {

    private String element;
    private long summa;

    @Override
    public void startDocument() throws SAXException {
        System.out.println(String.format("Начал вычислять сумму."));
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println(String.format("Закончил вычислять сумму."));
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        element = qName;
        if (element.equals("entry")) {
            summa += Long.parseLong(attributes.getValue(0));
        }


    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        element = "";
    }

    public Long getSumma() {
        return summa;
    }
}
