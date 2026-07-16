package uepa.aplicativo.DataManager;

import java.io.FileOutputStream;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.message.Message;
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

            writer.writeStartElement("userlist");
            for(User u : userList) {

                writer.writeStartElement("user");

                /* attributes of user */
                writeElement(writer, "name", u.getName());
                writeElement(writer, "email", u.getEmail());
                writeElement(writer, "password", u.getPassword());
                writeElement(writer, "photoPath", u.getPhotoPath());
                writeElement(writer, "role", u.getRole().toString());
                writeElement(writer, "id", u.getIdString());

                writer.writeStartElement("mailbox");
                for(Message m : u.getMailBox()) {

                    /* for every message we write a new element "message" */
                    writer.writeStartElement("message");

                    writeElement(writer, "title", m.getTitle());
                    writeElement(writer, "text", m.getText());
                    writeElement(writer, "creatorname", m.getCreatorName());

                    writer.writeEndElement();
                }

                /* close "mailbox" and "user" elements */
                writer.writeEndElement();
                writer.writeEndElement();
            }

            /* close the "userlist" element */
            writer.writeEndElement();

            closeWriter(writer);
        }
        catch (Exception e) {
            System.out.println("Failed to write XML File for User");
            e.printStackTrace();
        }

    }

    public static void writeExtracurriculars(String xmlPath, Data data) {
        try{
            XMLStreamWriter writer = startWriter(xmlPath);
            List<Extracurricular> extracurricularList = data.getExtracurricularList();

            writer.writeStartElement("extracurricularlist");
            for(Extracurricular e : extracurricularList) {

                writer.writeStartElement("extracurricular");

                /* attributes of extra */
                writeElement(writer, "name", e.getName());
                writeElement(writer, "description", e.getDescription());
                writeElement(writer, "institute", e.getInstitute());
                writeElement(writer, "isopenstring", e.getIsOpenString());
                writeElement(writer, "fxmlpath", e.getFxmlPath());
                writeElement(writer, "hyperlink", e.getHyperLink());
                writeElement(writer, "id", e.getIdString());

                writer.writeStartElement("staffids");
                for(int i[] : e.getStaffsIds()) {

                    /* for every staffId we write a new element "message" */
                    writeElement(writer, "idstaff", Integer.toString(i[0]));
                }
                writer.writeEndElement(); // close staffsids

                writer.writeStartElement("listenersids");
                for(int i[] : e.getListenersIds()) {
                    writeElement(writer, "idlistener", Integer.toString(i[0]));
                }
                writer.writeEndElement(); // close listernersIds
            
                writer.writeEndElement(); // close the Staff
            }

            /* close the "extracurricularlist" element */
            writer.writeEndElement();

            closeWriter(writer);
        }
        catch (Exception e) {
            System.out.println("Failed to write XML File for User");
            e.printStackTrace();
        }
    }


    private static void writeElement(XMLStreamWriter writer, String tag, String value) throws Exception{

        writer.writeStartElement(tag);

        if(value == null){
            writer.writeCharacters("");
        }
        else {
            writer.writeCharacters(value);
        }

        writer.writeEndElement();

    }
}
