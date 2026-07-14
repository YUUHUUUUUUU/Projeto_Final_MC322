package uepa.aplicativo.extracurricular;
import java.util.List;

import javafx.scene.image.Image;
import uepa.aplicativo.constants.*;
import uepa.aplicativo.extracurricular.gallery.PhotoGallery;
import uepa.aplicativo.user.Staff;
import uepa.aplicativo.user.Student;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public abstract class Extracurricular{
    private String name;
    private List<Student> studentsFollowing = new ArrayList<Student>();
    //private List<Tag> tags = new ArrayList<Tag>();
    private boolean openToWork;
    private String description;
    public Staff moderator;
    public boolean hasModerator;
    private ZonedDateTime timezone;
    private ZonedDateTime initialEnrollmentDate;
    private ZonedDateTime finalEnrollmentDate;
    private PhotoGallery photoGallery;

    private String fxmlPath;

    public Extracurricular(String name, String description) {
        setName(name);
        setDescription(description);

        photoGallery = new PhotoGallery("/logo/UEPA.png");
    }

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

//    public List<Tag> getListTags(){
//        return this.tags;
//    }

    public Image getLogo() {
        return photoGallery.getLogo();
    }

    public String getFxmlPath() {
        return fxmlPath;
    }

    public void setFxmlPath(String fxmlPath) {
        this.fxmlPath = fxmlPath;
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
            if (studentsFollowing.get(i).getID()==s.getID()){
                pos=i;
            }
        }
        studentsFollowing.remove(pos);
    }

    public boolean isFollowedBy(Student s){
        int t=studentsFollowing.size();
        for (int i=0;i<t;i++){
            if (studentsFollowing.get(i).getID()==s.getID()){
                return true;
            }
        }
        return false;
    }
}
