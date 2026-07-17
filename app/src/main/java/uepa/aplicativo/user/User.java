package uepa.aplicativo.user;
import java.util.ArrayList;
import java.util.List;

import uepa.aplicativo.constants.Role;
import uepa.aplicativo.interfaces.*;
import uepa.aplicativo.message.*;

import javafx.scene.image.Image;
import uepa.aplicativo.loaders.ImageLoader;

public class User implements Notificable{

    Role role;
    private String name;
    private String email;
    private String password;

    private Image photo;
    private String photoPath;

    private List<Message> mailBox;

    private static int nextId = 0;
    private final int id;
    
    /**
     * Represents the constructor the should be called when a new user is created.
     * This construct doesn't read a id. Because a new User doesn't have an id yet.
     * 
     * @param name
     * @param email
     * @param password
     * @param photoPath
     * @param mailBox
     */
    // criar novos
    public User(String name, String email, String password,
         String photoPath, List<Message> mailBox){

        this.id = nextId++; // gera id novo


        this.email = email;
        this.password = password;
        this.name = name;
        this.photoPath = photoPath;
        setPhoto(photoPath);
        this.mailBox = mailBox;
    }

    /**
     * Represents the constructor to reconstruct the User from XML.
     * This construct gets an id, to update the available ids for the registers.
     * This should be called only during the start of the application.
     * 
     * <p>
     * This Overflow is important because we need to guarantee that
     * every new register will come with a new id based on those saved on
     * the XML file
     * </p>
     * 
     * @param email
     * @param name
     * @param photoPath
     * @param id
     */
    // reconstruir
    public User(String name, String email,
         String password, String photoPath,
          List<Message> mailBox, String idString) {
        
        /* converts the idString back to int */
        this.id = Integer.parseInt(idString);
        updateNextId(id);

        this.email = email;
        this.name = name;
        this.password = password;
        this.photoPath = photoPath;
        this.mailBox = mailBox;
        setPhoto(photoPath);
    }

    /**
     * Represents the method to update the next id after loading the user data
     * @param existingId
     */
    public void updateNextId(int existingId) {
        if(existingId >= nextId) {
            nextId = existingId + 1;
        }
    }

    public String getIdString() {
        return Integer.toString(id);
    }

    public int getId() {
        return this.id;
    }

    public Role getRole() {
        return role;
    }

    public Image getPhoto() {
        return new Image(getClass().getResourceAsStream(this.photoPath));
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public void setPhoto(String photoPath) {
        try {
            photo = ImageLoader.LoadImage(photoPath);
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        // Check for null or empty strings
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }

        // Check length constraints
        if(name.length() > 50){
            throw new IllegalArgumentException("Name cannot exceed 50 characters.");
        }

        this.name = name;
    }

    public String getPassword(){
        return this.password;
    }
    
    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
       if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        this.email = email.trim();
    }

    public void changePassword(String newPassword){
        // Null check
        if(password == null || password.trim().isEmpty()){
            throw new IllegalArgumentException("Password cannot be empty.");
        }
        // Length check
        if(password.length() < 8){
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }
        // Uppercase letter check
        if(!password.matches(".*[A-Z].*")){
            throw new IllegalArgumentException("Password must contain at least one uppercase letter.");
        }
        // Lowercase letter check
        if(!password.matches(".*[a-z].*")){
            throw new IllegalArgumentException("Password must contain at least one lowercase letter.");
        }
        // Special character check
        if(!password.matches(".*[^a-zA-Z0-9].*")){
            throw new IllegalArgumentException("Password must contain at least one special symbol.");
        }
        // No spaces check
        if(password.contains(" ")){
            throw new IllegalArgumentException("Password cannot contain spaces.");
        }
        // Assign the password
        this.password = newPassword; 
    }

    public List<Message> getMailBox(){
        return mailBox;
    }

    public boolean removeMailBox(Message m) {
        if (m == null) {
            return false;
        }
        return this.mailBox.remove(m);
    }

    @Override
    public void receiveMessage(Message m) {
        if (m == null) {
            return;
        }
        mailBox.add(m);
    }
}