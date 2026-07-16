package uepa.aplicativo.user;
import java.util.List;

import uepa.aplicativo.constantes.Tag;
import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.interfaces.Favoritable;
import uepa.aplicativo.interfaces.Notifiable;

import java.util.ArrayList;

public class Student extends User{
    private List<Favoritable> favorites = new ArrayList<Favoritable>();
    private List<Notifiable> following = new ArrayList<Notifiable>();
    private List<Notifiable> tags = new ArrayList<Notifiable>();

    Student(String email,String name){
        super(email,name);
    }

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
