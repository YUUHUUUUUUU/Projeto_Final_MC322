package uepa.aplicativo.user;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTML.Tag;

import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.interfaces.Notifiable;
import uepa.aplicativo.message.Message;

public class User implements Notifiable{
    private String name;
    private String email;
    private String password;
    private List<Tag> followedTags;
    private List<Extracurricular> favorites;
    private List<Extracurricular> followedExtras;
    private List<Message> mailBox;
    private int id;
    
    User(String email, String name){
        this.email = email;
        this.name = name;
        this.followedTags = new ArrayList<>();
        this.favorites = new ArrayList<>();
        this.followedExtras = new ArrayList<>();
        this.mailBox = new ArrayList<>();

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

        this.name = name.trim();
    }

     public int getID(){
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
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

    public List<Tag> getTags(){
        return followedTags;
    }

     public List<Extracurricular> getFavorites(){
        return favorites;
    }

    public List<Extracurricular> getFollowed(){
        return followedExtras;
    }
    public boolean removeTag(Tag tag) {
        if (tag == null) {
            return false;
        }
        return this.followedTags.remove(tag);
    }

    public boolean addTag(Tag tag) {
        if (tag == null) {
            return false;
        }
        // Evita adicionar a mesma tag repetida
        if (this.followedTags.contains(tag)) {
            return false; 
        }
        return this.followedTags.add(tag);
    }
    public boolean removeFavorite(Extracurricular extra) {
        if (extra == null) {
            return false;
        }
        return this.favorites.remove(extra);
    }
    public boolean addFavorite(Extracurricular extra) {
        if (extra == null) {
            return false;
        }
        if (this.favorites.contains(extra)) {
            return false; 
        }
        return this.favorites.add(extra);
    }
    public boolean removeFollowed(Extracurricular extra) {
        if (extra == null) {
            return false;
        }
        return this.followedExtras.remove(extra);
    }
    public boolean addFollowed(Extracurricular extra) {
        if (extra == null) {
            return false;
        }
        if (this.followedExtras.contains(extra)) {
            return false; 
        }
        return this.followedExtras.add(extra);
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
    
    
    // public List<News> getNews(){
    //     return this.latest_news;
    // }

    // public void addTag(){

    // }

    // public void follow_new_extra(){

    // }

    // public boolean login(){
    //     //do the login
    //     logged=true;
    //     return logged;
    // }

    // public void checkin(){

    // }

    // public void logout(){

    // }
}