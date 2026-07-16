package uepa.aplicativo.user;
import uepa.aplicativo.constants.Role;
import uepa.aplicativo.extracurricular.Extracurricular;

public class Student extends User{
    
    
    public Student(String email,String name, String photoPath){
        super(email, name, photoPath);
        setRole(Role.STUDENT);
    }
}
