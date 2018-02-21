package sqlFinal;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/*
Class Handler for parsing XML file.
 */
public class Handler extends DefaultHandler {

    private ArrayList<String> arrayList = new ArrayList<>();
    private String element = "";

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parse....");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End parse....");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        element = qName;

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        element = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String fieldValue = "";


        if (element.equals("field")) {


            for (int i = 0; i < length; i++) {
                fieldValue += ch[start + i];
            }
            arrayList.add(fieldValue);
        }
    }

    public ArrayList<String> getData() {
        return arrayList;
    }
}
