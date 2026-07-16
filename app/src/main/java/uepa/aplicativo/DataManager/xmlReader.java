package uepa.aplicativo.DataManager;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import uepa.aplicativo.message.Message;
import uepa.aplicativo.user.User;

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

    public List<User> readUsers(String xmlPath) {
        try{
            XMLStreamReader reader = startReader(xmlPath);

            String currentTag = null;
            User currentUser = null;

            boolean insideMailBox = false;
            List<Message> currentMailBox = null;
            String messageTitle = null;
            String messageText = null;
            String messageCreator = null;


            String userName = null;
            String userEmail = null;
            String userPassword = null;
            String userPhotoPath = null;

            while(reader.hasNext()) {

                /* This will give us the parsings of the xmlFile,
                 * it will be on the form of XMLStreamConstants.
                 * Some examples are: XMLStreamConstants.START_ELEMENT,
                 * XMLStreamConstants.CHARACTERS, XMLStreamConstants.END_ELEMENT.
                 * And those will be all the ones that we are going to need to read our
                 * xml file.
                 */
                int event = reader.next();

                /* If we use if-elses this part will get visually flooded 
                 * so we shall use the switch struct
                 */

                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        currentTag = reader.getLocalName();
                        if(currentTag.equals("user")) {
                            currentUser = new User();
                            currentMailBox = new ArrayList<>();
                        }
                        else if(currentTag.equals("mailbox")){

                            /* we got inside the mail box, so we need to indicate it,
                             * so we can proceed with the mailbox read
                             */
                            insideMailBox = true;
                        }
                        
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        if(!reader.isWhiteSpace() && currentTag != null){
                            String text = reader.getText();

                            if(insideMailBox) {
                                switch (currentTag) {
                                    case "title" -> messageTitle = text;
                                    case "text" -> messageText = text;
                                    case "creatorname" -> messageCreator = text;
                                }
                            }

                            else if(currentUser != null) {
                                switch (text) {
                                    case "name" -> userName = text;
                                    case "email" -> userEmail = text;
                                    case "password" -> userPassword = text;
                                    case "photoPath" -> userPhotoPath = text;
                                }
                            }
                        }
                        break;
                }

            }




            closeReader(reader);
        }
        catch(Exception e) {
            System.out.println("Failed to load Users");
            e.printStackTrace();
        }


    }
}
