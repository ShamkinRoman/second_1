package sql;

import com.sun.xml.internal.bind.v2.runtime.JAXBContextImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class jabxWrite {


    public static void main(String[] args) {



        xmlCreate xmlCreate = new xmlCreate();

        xmlCreate.setField(20);//setNumer(20);


        File file = new File("d:\\1.xml");
        try {
            JAXBContext jaxbContext= JAXBContext.newInstance(xmlCreate.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(xmlCreate, file);
            marshaller.marshal(xmlCreate, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }



    }

}

