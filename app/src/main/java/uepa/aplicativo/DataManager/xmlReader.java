package uepa.aplicativo.DataManager;

import java.io.FileInputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

public class xmlReader {

    private static XMLStreamReader startReader(String xmlPath) throws Exception{
        String absolutePath = System.getProperty("user.dir") + xmlPath;
        System.out.println(absolutePath);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        FileInputStream file = new FileInputStream(absolutePath);
        XMLStreamReader reader = factory.createXMLStreamReader(file);

        return reader;
    }
    
    private static void closeReader(XMLStreamReader reader) throws Exception{
        reader.close();
    }
}
