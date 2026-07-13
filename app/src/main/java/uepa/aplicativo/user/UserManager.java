package uepa.aplicativo.user;

import java.io.File;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class UserManager {
    
    private static String XML_FILE = "users.xml";

    // Validation Methods

    private void validateName(String name){
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

    private void validateEmail(String email){
        if(email == null || email.trim().isEmpty()){
            throw new IllegalArgumentException("Email cannot be empty.");
        }
        
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        
        if(!email.matches(emailRegex)){
            throw new IllegalArgumentException("Invalid email format.");
        }
    }

    private void validatePassword(String password){
        if(password == null || password.isEmpty()){
            throw new IllegalArgumentException("Password cannot be empty.");
        }
        if(password.matches(".*\\s.*")){
            throw new IllegalArgumentException("Password cannot contain spaces.");
        }
        if(password.length() < 8){
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }
        if(!password.matches(".*[A-Z].*")){
            throw new IllegalArgumentException("Password must contain at least one uppercase letter.");
        }
        if(!password.matches(".*[a-z].*")){
            throw new IllegalArgumentException("Password must contain at least one lowercase letter.");
        }
        if(!password.matches(".*[^a-zA-Z0-9].*")){
            throw new IllegalArgumentException("Password must contain at least one special symbol.");
        }
    }

    // Criptography

    private String generateHash(String email, String plainPassword) {
        try {
            String textToHash = email + plainPassword;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(textToHash.getBytes(StandardCharsets.UTF_8));
            
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
            
        }catch (Exception e) {
            throw new RuntimeException("Error processing password encryption.", e);
        }
    }

    // Main methods

    public void signIn(String name, String email, String plainPassword){
        // Validation
        validateName(name);
        validateEmail(email);
        validatePassword(plainPassword);

        // Format name and email
        String cleanName = name.trim();
        String fullEmail = email.trim();

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

            Element nameNode = doc.createElement("Name");
            nameNode.appendChild(doc.createTextNode(cleanName));
            userElement.appendChild(nameNode);

            Element hashNode = doc.createElement("PasswordHash");
            hashNode.appendChild(doc.createTextNode(passwordHash));
            userElement.appendChild(hashNode);

            Element favorites = doc.createElement("Favorites");
            Element following = doc.createElement("Following");
            Element tags = doc.createElement("Tags");

            userElement.appendChild(favorites);
            userElement.appendChild(following);
            userElement.appendChild(tags);

            // Attach the new user to the root
            rootElement.appendChild(userElement);

            // Write the updated document back to the file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);

        }catch (IllegalArgumentException e){
            throw e;
        }catch(Exception e) {
            throw new RuntimeException("Error writing user to XML file.", e);
        }
    }

    public User login(String fullEmail, String typedPassword) {
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
        }catch (IllegalArgumentException e){
            throw e;
        }catch(Exception e){
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
        User loggedInUser = new Student(fullEmail, savedName);
        
        return loggedInUser;
    }
}