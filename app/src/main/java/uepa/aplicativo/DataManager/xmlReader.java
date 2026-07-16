package uepa.aplicativo.DataManager;

import java.io.FileInputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

public class xmlReader {

    public static XMLStreamReader startReader(String xmlPath) throws Exception{
        String absolutePath = System.getProperty("user.dir") + xmlPath;
        System.out.println(absolutePath);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        FileInputStream file = new FileInputStream(absolutePath);
        XMLStreamReader reader = factory.createXMLStreamReader(file);

        return reader;
    }
    
}
