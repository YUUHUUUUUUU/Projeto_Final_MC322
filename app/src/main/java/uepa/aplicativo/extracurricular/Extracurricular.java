package uepa.aplicativo.extracurricular;
import java.util.List;

import constantes.Tag;
import uepa.aplicativo.interfaces.Favoritable;
import uepa.aplicativo.interfaces.Notifiable;
import uepa.aplicativo.user.Staff;
import uepa.aplicativo.user.Student;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public abstract class Extracurricular implements Notifiable,Favoritable {
    private String name;
    private List<Student> studentsFollowing = new ArrayList<Student>();
    private List<Tag> tags = new ArrayList<Tag>();
    private boolean openToWork;
    private String description;
    public Staff moderator;
    public boolean hasModerator;
    private ZonedDateTime timezone;
    private ZonedDateTime initialEnrollmentDate;
    private ZonedDateTime finalEnrollmentDate;

    public String getName(){
        return this.name;
    }

    public boolean setName(String n){
        this.name=n;
        return true;
    }

    public boolean setDescription(String d){
        this.description=d;
        return true;
    }

    public boolean setEnrollment(boolean b){
        this.openToWork=b;
        return b;
    }

    public ZonedDateTime getInitial(){
        return this.initialEnrollmentDate;
    }

    public ZonedDateTime getFinal(){
        return this.finalEnrollmentDate;
    }

    public boolean checkEnrollment(){
        return openToWork;
    }

    public List<Tag> getListTags(){
        return this.tags;
    }

    public boolean open_to_work(){
        return this.openToWork;
    }

    public String getDescription(){
        return this.description;
    }

    public void addtoNotify(Student s){
        studentsFollowing.add(s);
    }

    public void removefromNotify(Student s){
        int t=studentsFollowing.size();
        int pos=0;
        for (int i=0;i<t;i++){
            if (studentsFollowing[i].id==s.id){
                pos=i;
            }
        }
        studentsFollowing.remove(pos);
    }

    public boolean isFollowedBy(Student s){
        int t=studentsFollowing.size();
        for (int i=0;i<t;i++){
            if (s.id==studentsFollowing[i]){
                return true;
            }
        }
        return false;
    }
}
