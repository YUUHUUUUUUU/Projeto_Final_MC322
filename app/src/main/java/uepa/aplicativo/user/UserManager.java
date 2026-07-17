package uepa.aplicativo.user;

import uepa.aplicativo.DataManager.Data;
import uepa.aplicativo.Exceptions.InvalidEmailException;
import uepa.aplicativo.Exceptions.InvalidPasswordException;

public class UserManager {
    
    private UserManager() {}

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

    // Main methods

    public static void signIn(String name, String fullEmail,
         String plainPassword, String plainConfirmedPassword, Data data) throws Exception{
        
        // Validation
        validateName(name);
        validateEmail(fullEmail);
        validatePassword(plainPassword, plainConfirmedPassword);
        
        Student student = new Student(fullEmail, name, plainPassword, "/logo/UEPA.png");
        data.addUser(student);
    }

    public static User login(String typedFullEmail, String typedPassword, Data data) throws Exception{
        for(User u : data.getUserList()) {
            if(compareLoginEmails(typedFullEmail, u.getEmail()) &&
                compareLoginPasswords(typedPassword, u.getPassword())) {
                    return u;
            }
        }
        throw new Exception("Email or password wrong");
    }

    private static boolean compareLoginEmails(String email1, String email2) throws Exception{
        if(email1 != null && email2 != null) {
            if(email1.equals(email2)) {
                return true;
            }
        }

        return false;
    }

    private static boolean compareLoginPasswords(String password1, String password2) throws Exception{
        if(password1 != null && password2 == null) {
            if(password1.equals(password2)) {
                return true;
            }
        }

        return false;
    }
}