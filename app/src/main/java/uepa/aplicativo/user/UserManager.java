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
import uepa.aplicativo.Exceptions.InvalidPasswordException;

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
    
    /**
     * Represents a method to validate the syntax of the Email Local Part, it does not validates
     * if an email exists.
     *  
     * @param fullEmail full email String
     * @throws InvalidEmailException
     */
    private static void validateEmailLocalPart(String fullEmail) throws InvalidEmailException{
        
        /* We verify the position of the @, .indexOf() returns -1 if there is not a match for the char */
        int atIndex = fullEmail.indexOf('@');

        if(atIndex == -1) {
            throw new InvalidEmailException("Email is missing '@' character");
        }
        
        /* We subdivide the subpart */
        String localPart = fullEmail.substring(0, atIndex);

        /* We throw the Exceptions */
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


    /**
     * Represents a method to validate the Email Domain syntax, it does not check if the email or 
     * the email domain exists.
     * 
     * @param fullEmail full email String 
     * @throws InvalidEmailException
     */
    private static void validateEmailDomain(String fullEmail) throws InvalidEmailException {

        int atIndex = fullEmail.indexOf('@');
        
        if(atIndex == -1) {
            throw new InvalidEmailException("Email is missing '@' character");
        }

        String domain = fullEmail.substring(atIndex + 1);

        if(domain.isEmpty()) {
            throw new InvalidEmailException("Email domain can not be empty");
        }
        else if(domain.length() > 255) {
            throw new InvalidEmailException("Email domain exceeds 255 characters");
        }
        else if(!domain.contains(".")) {
            throw new InvalidEmailException("Email domain must contain a Top-Level-Domain (TLD)");
        }
        else if(domain.startsWith("-") || domain.endsWith("-") || domain.startsWith(".") || domain.endsWith(".")) {
            throw new InvalidEmailException("Email domain cannot start or end with '-' or '.'");
        }
        else if(domain.contains("..")) {
            throw new InvalidEmailException("Email domain cannot contain consecutive dots");
        }
        else if(!domain.matches("^[a-zA-Z0-9.-]+$")) {
            throw new InvalidEmailException("Email domain contains an illegal character");
        }

        String topLevelDomain = domain.substring(domain.lastIndexOf('.') + 1);
        if (topLevelDomain.length() < 2 || !topLevelDomain.matches("^[a-zA-Z]+$")) {
            throw new InvalidEmailException("Email Top-Level-Domain (TLD) must have at least 2 letters");
        }

    }

    /**
     * Represents a method to check if the email length is valid
     * @param fullEmail full email String
     * @throws InvalidEmailException
     */
    private static void validateEmailLength(String fullEmail) throws InvalidEmailException {
        if(fullEmail.length() > 320) {
            throw new InvalidEmailException("Total email length exceeds 254 characters");
        }
    }

    /**
     * Represents a method to check if the email syntax is valid, it does not check
     * if the email exists. Only if the syntax is  according to RFC 5321 norms.
     * 
     * @param fullEmail full email String
     * @throws Exception
     */
    private static void validateEmail(String fullEmail) throws Exception{
        validateEmailLocalPart(fullEmail);
        validateEmailDomain(fullEmail);
        validateEmailLength(fullEmail);
    }


    /**
     * Represents a method to validate the integrity of a password
     * 
     * @param password plain password String
     * @throws InvalidPasswordException
     */

    private static void validatePasswordSyntax(String password) throws InvalidPasswordException {
        if(password == null || password.isEmpty()){
            throw new InvalidPasswordException("Password cannot be empty.");
        }
        if(password.matches(".*\\s.*")){
            throw new InvalidPasswordException("Password cannot contain spaces.");
        }
        if(password.length() < 8){
            throw new InvalidPasswordException("Password must be at least 8 characters long.");
        }
        if(!password.matches(".*[A-Z].*")){
            throw new InvalidPasswordException("Password must contain at least one uppercase letter.");
        }
        if(!password.matches(".*[a-z].*")){
            throw new InvalidPasswordException("Password must contain at least one lowercase letter.");
        }
        if(!password.matches(".*[^a-zA-Z0-9].*")){
            throw new InvalidPasswordException("Password must contain at least one special symbol.");
        }
    }

    /** 
     * Represents a simple method that compares the String of two passwords.
     * 
     * <p>
     * This method is used to compare if the password and the confirmation of the password
     * in the RegisterPage matches.
     * </p>
     * 
     * @param plainPassword plain String password, in other others, not encrypted.
     * @param plainConfirmedPassword plain String confirmation of the password, in other others, not encrypted.
     * 
     * @throws PasswordsDoNotMatchException
     */
    private static void comparePasswords(String plainPassword,
         String plainConfirmedPassword) throws InvalidPasswordException {

        if(!plainPassword.equals(plainConfirmedPassword)) {
            throw new InvalidPasswordException("Passwords does not match");
        }
    }

    /**
     * Represents a method to validate a password during the register of an account.
     * 
     * @param plainPassword plain password String
     * @param plainConfirmedPassword plain password String
     * @throws InvalidPasswordException
     */
    private static void validatePassword(String plainPassword,
         String plainConfirmedPassword) throws InvalidPasswordException {
        validatePasswordSyntax(plainPassword);
        validatePasswordSyntax(plainConfirmedPassword);
        comparePasswords(plainPassword, plainConfirmedPassword);
    }


    // Criptography

    private static String generateHash(String email, String plainPassword) {
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
            
        } catch (Exception e) {
            throw new RuntimeException("Error processing password encryption.", e);
        }
    }

    

    // Main methods

    public static void signIn(String name, String fullEmail,
         String plainPassword, String plainConfirmedPassword) throws Exception{

        // Validation
        validateName(name);
        validateEmail(fullEmail);
        validatePassword(plainPassword, plainConfirmedPassword);

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