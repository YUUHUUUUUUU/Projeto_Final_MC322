package uepa.aplicativo.user;
import java.util.List;

import constantes.Tag;

import java.util.ArrayList;

public class User{//abstract?
    private String name;
    private String email;
    private String password;
    private List<Tag> followed_tags = new ArrayList<Tag>();
    private int id;
    // private List<Extracurricular> favorites = new ArrayList<Extracurricular>();
    // private List<Extracurricular> followed = new ArrayList<Extracurricular>();
    // private List<News> latest_news = new ArrayList<News>();

    User(String email, String name){
        this.email = email;
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public int getID(){
        return this.id;
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

    public String getPassword(){
        return this.password;
    }
    
    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
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
        return this.followed_tags;
    }

    // public List<Extracurricular> getFavorites(){
    //     return this.favorites;
    // }

    // public List<Extracurricular> getFollowed(){
    //     return this.followed;
    // }
    
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