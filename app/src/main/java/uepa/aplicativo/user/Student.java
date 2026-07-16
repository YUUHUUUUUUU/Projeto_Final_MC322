package uepa.aplicativo.user;
import uepa.aplicativo.extracurricular.Extracurricular;

public class Student extends User{
    Student(String email,String name, String photoPath){
        super(email, name, photoPath);
    }

    public void favorite(Extracurricular e){
        //favorites.add(e); aqui vai ficar com problema mesmo, extracurricular so pode ser 1 dos dois
    }

    public void notify(Extracurricular e){
        
    }

    //public void addExtra(Extracurricular e){
    //    following.add(e);
    //}

    public void logout(){

    }
}
