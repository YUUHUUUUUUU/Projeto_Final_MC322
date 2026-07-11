package uepa.aplicativo.usuarios;
import java.util.List;
import java.util.ArrayList;

public class Student extends User{
    private List<Favoritable> favorites = new ArrayList<Favoritable>();
    private List<Notifiable> following = new ArrayList<Notifiable>();
    private List<Notifiable> tags = new ArrayList<Notifiable>();

    public void favorite(Extracurricular e){
        //favorites.add(e); aqui vai ficar com problema mesmo, extracurricular so pode ser 1 dos dois
    }

    public void addTag(Tag t){
        tags.add(t);
    }

    public void notify(Extracurricular e){
        
    }

    public void addExtra(Extracurricular e){
        following.add(e);
    }

    public void logout(){

    }
}
