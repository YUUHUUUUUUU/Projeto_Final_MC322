package uepa.aplicativo.DataManager;

import java.io.FileOutputStream;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import uepa.aplicativo.user.User;

public class xmlWriter {
    
    private static XMLStreamWriter startWriter(String xmlPath) throws Exception{
        String absolutePath = System.getProperty("user.dir") + xmlPath;
        System.out.println(absolutePath);
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        FileOutputStream file = new FileOutputStream(absolutePath);
        XMLStreamWriter writer = factory.createXMLStreamWriter(file, "UTF-8");

        writer.writeStartDocument("UTF-8", "1.0");

        return writer;
    }

    private static void closeWriter(XMLStreamWriter writer) throws Exception{
        writer.flush();
        writer.close();

    }

    public static void writeUsers(String xmlPath, Data data) {
        try{
            XMLStreamWriter writer = startWriter(xmlPath);
            List<User> userList = data.getUserList();

            for(User u : userList) {
                writer.writeStartElement("userList");
                writer.writeStartElement("User");
                writer.writeAttribute("name", u.getName());
                writer.writeAttribute("email", u.getEmail());
                writer.writeAttribute("photoPath", u.getPhotoPath());
            }

            closeWriter(writer);
        }
        catch (Exception e) {
            System.out.println("Failed to write XML File for User");
            e.printStackTrace();
        }

    }
}
