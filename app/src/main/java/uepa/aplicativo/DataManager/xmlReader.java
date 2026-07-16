package uepa.aplicativo.DataManager;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import uepa.aplicativo.constants.Role;
import uepa.aplicativo.message.Message;
import uepa.aplicativo.user.Staff;
import uepa.aplicativo.user.Student;
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

    public static Data readUsers(String xmlPath) {

        List<User> userList = new ArrayList<>();
        List<Staff> staffList = new ArrayList<>();
        List<Student> studentList = new ArrayList<>();

        try{
            XMLStreamReader reader = startReader(xmlPath);

            String currentTag = null;

            boolean insideMailBox = false;
            List<Message> currentMailBox = null;
            String messageTitle = null;
            String messageText = null;
            String messageCreator = null;

            Staff currentStaff = null;
            Student currentStudent = null;
            String userName = null;
            String userEmail = null;
            String userPassword = null;
            String userPhotoPath = null;
            String userRole = null;

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

                    /*
                     * In this part we will just need to create a new MailBox for each user that
                     * we are reading
                     */
                    case XMLStreamConstants.START_ELEMENT:
                        currentTag = reader.getLocalName();
                        if(currentTag.equals("user")) {

                            /* for each user we need to create a mailbox */
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

                            else {
                                switch (currentTag) {
                                    case "name" -> userName = text;
                                    case "email" -> userEmail = text;
                                    case "password" -> userPassword = text;
                                    case "photoPath" -> userPhotoPath = text;
                                    case "role" -> userRole = text;
                                }
                            }
                        }
                        break;
                    
                    /*
                     * In this part we instantiate the elements
                     */
                    case XMLStreamConstants.END_ELEMENT:
                        String tag = reader.getLocalName();

                        /* the last attribute of the mail box message, this implies that we can create
                         * the full message
                        */
                        if(tag.equals("creatorname")) {
                            Message message = new Message(messageTitle, messageText, messageCreator);
                            currentMailBox.add(message);
                        }

                        /* the end of the mail box,
                         * so we can set that we are out of the mail box
                         */
                        else if(tag.equals("mailbox")) {
                            insideMailBox = false;
                        }

                        /* we create the user, add user to the list and points every reference to null */
                        else if(tag.equals("user")) {
                            /* verify which role this user belongs */
                            if(userRole == Role.STAFF.toString()) {
                                currentStaff = new Staff(userName, userEmail, userPassword, userPhotoPath, currentMailBox);
                                staffList.add(currentStaff);
                            }
                            else if(userRole == Role.STUDENT.toString()) {
                                currentStudent = new Student(userName, userEmail, userPassword, userPhotoPath, currentMailBox);
                                studentList.add(currentStudent);
                            }
                            currentStaff = null;
                            currentStudent = null;
                            currentMailBox = null;
                        }
                        
                        currentTag = null;
                        break;
                }

            }

            closeReader(reader);
        }
        catch(Exception e) {
            System.out.println("Failed to load Users");
            e.printStackTrace();
        }

        Data data = new Data(studentList, staffList);
        return data;
    }
}
