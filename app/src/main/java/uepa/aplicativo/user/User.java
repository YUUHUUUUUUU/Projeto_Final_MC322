package uepa.aplicativo.user;
import java.util.List;
import java.util.ArrayList;

public abstract class User{
    private String name;
    private String email;
    private int id;
    // private List<Extracurricular> favorites = new ArrayList<Extracurricular>();
    // private List<Extracurricular> followed = new ArrayList<Extracurricular>();
    // private List<News> latest_news = new ArrayList<News>();

    public User(String email, String name){
        this.email = email;
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public void setID(int id){
        this.id = id;
    }

    public int getID(){
        return this.id;
    }

    // For changing the name of an existing user, although I don't think we will inplement the
    // front end for that yet
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
}