package uepa.aplicativo.user;
import java.util.List;

import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.message.Message;
import uepa.aplicativo.constants.Role;

public class Student extends User{
    public Student(String email,String name, String password, String photoPath, List<Message> mailBox){
        super(email, name, password, photoPath, mailBox);
        role = Role.STUDENT;
    }

    public Student(String email,String name, String password, String photoPath, List<Message> mailBox, String idString){
        super(email, name, password, photoPath, mailBox, idString);
        role = Role.STUDENT;
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
