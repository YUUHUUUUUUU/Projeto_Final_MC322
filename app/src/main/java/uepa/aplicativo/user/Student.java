package uepa.aplicativo.user;
import java.util.List;

import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.message.Message;
import uepa.aplicativo.constants.Role;

public class Student extends User{
    public Student(String name, String email, String password, String photoPath){
        super(name, email, password, photoPath);
        role = Role.STUDENT;
    }

    public Student(String name, String email, String password, String photoPath, List<Message> mailBox, String idString){
        super(name, email, password, photoPath, mailBox, idString);
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
