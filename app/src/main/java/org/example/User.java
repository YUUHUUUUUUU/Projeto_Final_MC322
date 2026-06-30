package src;
import java.util.List;
import java.util.ArrayList;

public abstract class User{
    private String name;
    private String email;
    private String password;
    private List<Tag> followed_tags = new ArrayList<Tag>();
    private int id;
    // private List<Extracurricular> favorites = new ArrayList<Extracurricular>();
    // private List<Extracurricular> followed = new ArrayList<Extracurricular>();
    // private List<News> latest_news = new ArrayList<News>();

    public String getName(){
        return this.name;
    }

    public void setName(){

    }

    public String getPassword(){
        return this.password;
    }
    
    public String getEmail(){
        return this.email;
    }

    public void setEmail(){

    }

    public boolean autenticate(String email,String password){
        return true;
    }

    public void changePassword(String newPassword){

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