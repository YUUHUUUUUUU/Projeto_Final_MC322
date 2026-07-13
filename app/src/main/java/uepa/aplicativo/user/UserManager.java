package uepa.aplicativo.user;

import java.io.File;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import uepa.aplicativo.Exceptions.EmailIllegalCharacterException;
import uepa.aplicativo.Exceptions.EmailLocalPartCanNotBeEmpty;
import uepa.aplicativo.Exceptions.EmailLocalPartIsTooLong;
import uepa.aplicativo.Exceptions.InvalidEmailException;
import uepa.aplicativo.Exceptions.PasswordsDoNotMatchException;

public class UserManager {
    
    /* We don't want to instantiate it, only use its methods */
    private UserManager() {}


    private static String XML_FILE = "users.xml";

    // Validation Methods

    private static void validateName(String name){
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if(name.length() > 50){
            throw new IllegalArgumentException("Name cannot exceed 50 characters.");
        }
        if(!name.matches("^[\\p{L} \\-']+$")){
            throw new IllegalArgumentException("Name contains invalid special characters.");
        }
    }

    private static void validateEmailLocalPart(String fullEmail) throws Exception{
        
        /* We verify the position of the @, .indexOf() returns -1 if there is not a match for the char */
        int atIndex = fullEmail.indexOf('@');

        if(atIndex == -1) {
            throw new InvalidEmailException("Email is missing '@' character");
        }
        
        /* We subdivide the subpart */
        String localPart = fullEmail.substring(0, atIndex);

        if(localPart.isEmpty()) {
            throw new InvalidEmailException("Email local part can not be empty");
        }
        else if(localPart.length() > 64) {
            throw new InvalidEmailException("Email local part exceeds 64 characters");
        }
        else if(!localPart.matches("^[a-zA-Z0-9._%+-]+$")) {
            throw new InvalidEmailException("Email local part contains illegal character: " + localPart);
        }
    }

    private static void validateEmail(String fullEmail){
        validateEmailLocalPart(fullEmail);
    }



    
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
            
        } catch (Exception e) {
            throw new RuntimeException("Error processing password encryption.", e);
        }
    }

    // Main methods

    public static void signIn(String name, String fullEmail,
         String plainPassword, String plainConfirmedPassword) throws Exception{

        // Validation
        validateName(name);
        validateEmailPrefix(email);
        validatePassword(plainPassword);
        validatePassword(plainConfirmedPassword);
        comparePasswords(plainPassword, plainConfirmedPassword);
        

        // Format name and email
        String cleanName = name.trim();

        // Generate hash
        String passwordHash = generateHash(fullEmail, plainPassword);

        // Write to XML Database
        try{
            File xmlFile = new File(XML_FILE);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc;
            Element rootElement;

            if(xmlFile.exists()){
                doc = builder.parse(xmlFile);
                rootElement = (Element) doc.getElementsByTagName("Users").item(0);

                // Duplicate check
                NodeList nodeList = doc.getElementsByTagName("User");
                for(int i = 0; i < nodeList.getLength(); i++){
                    Element userNode = (Element) nodeList.item(i);
                    String existingEmail = userNode.getElementsByTagName("Email").item(0).getTextContent();
                    
                    if(existingEmail.equals(fullEmail)){
                        throw new IllegalArgumentException("An account with this email already exists.");
                    }
                }
            }else{
                doc = builder.newDocument();
                rootElement = doc.createElement("Users");
                doc.appendChild(rootElement);
            }

            // Create the new <User> element
            Element userElement = doc.createElement("User");

            Element emailNode = doc.createElement("Email");
            emailNode.appendChild(doc.createTextNode(fullEmail));
            userElement.appendChild(emailNode);

            Element nameNode = doc.createElement("Nome");
            nameNode.appendChild(doc.createTextNode(cleanName));
            userElement.appendChild(nameNode);

            Element hashNode = doc.createElement("PasswordHash");
            hashNode.appendChild(doc.createTextNode(passwordHash));
            userElement.appendChild(hashNode);

            // Attach the new user to the root
            rootElement.appendChild(userElement);

            // Write the updated document back to the file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);

        } catch (Exception e) {
            throw new RuntimeException("Error writing user to XML file.", e);
        }
    }

    public static User login(String fullEmail, String typedPassword) {
        String savedHash = null;
        String savedName = null;
        boolean userFound = false;

        // Read from XML Database
        try {
            File xmlFile = new File(XML_FILE);
            if (!xmlFile.exists()) {
                throw new IllegalArgumentException("No registered users found.");
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Get all <User> tags
            NodeList nodeList = doc.getElementsByTagName("User");

            // Loop through them to find the matching email
            for(int i = 0; i < nodeList.getLength(); i++){
                Element userNode = (Element) nodeList.item(i);
                String nodeEmail = userNode.getElementsByTagName("Email").item(0).getTextContent();

                if(nodeEmail.equals(fullEmail)){
                    savedName = userNode.getElementsByTagName("Nome").item(0).getTextContent();
                    savedHash = userNode.getElementsByTagName("PasswordHash").item(0).getTextContent();
                    userFound = true;
                    break; 
                }
            }
        }catch(Exception e) {
            throw new RuntimeException("Error reading from XML file.", e);
        }

        // If the loop finished and no user was found with that email
        if(!userFound){
            throw new IllegalArgumentException("Invalid email or password.");
        }

        // Verify the hash
        String attemptHash = generateHash(fullEmail, typedPassword);
        if(!attemptHash.equals(savedHash)){
            throw new IllegalArgumentException("Invalid email or password.");
        }

        // Return the authenticated User object (without the password)
        User loggedInUser = new User(fullEmail, savedName);
        
        return loggedInUser;
    }
}