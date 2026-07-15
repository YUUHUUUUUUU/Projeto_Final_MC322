package uepa.aplicativo.extracurricular;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import uepa.aplicativo.extracurricular.gallery.PhotoGallery;
import uepa.aplicativo.message.Message;
import uepa.aplicativo.user.Staff;
import uepa.aplicativo.user.User;

public abstract class Extracurricular {
    private String name;
    private ArrayList<User> listners;
    //private List<Tag> tags = new ArrayList<Tag>();
    private boolean openToWork;
    private String description;
    public Staff moderator;
    public boolean hasModerator;
    private ZonedDateTime timezone;
    private ZonedDateTime initialEnrollmentDate;
    private ZonedDateTime finalEnrollmentDate;
    private PhotoGallery photoGallery;

    public Extracurricular(String name, String description) {
        setName(name);
        setDescription(description);
        this.listners = new ArrayList<>();

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

    public boolean open_to_work(){
        return this.openToWork;
    }

    public String getDescription(){
        return this.description;
    }
    public List<User> getUsersFollowing(){
        return listners;
    }

    public void addtoNotify(User s){
        listners.add(s);
    }

    public void removefromNotify(User s){
        int t=listners.size();
        int pos=0;
        for (int i=0;i<t;i++){
            if (listners.get(i).getID()==s.getID()){
                pos=i;
            }
        }
        listners.remove(pos);
    }

    public boolean isFollowedBy(User s){
        int t= listners.size();
        for (int i=0;i<t;i++){
            if (listners.get(i).getID()==s.getID()){
                return true;
            }
        }
        return false;
    }

    public void notityListners(Message m) {
        for (int i = 0; i < listners.size(); i++) {
            listners.get(i).receiveMessage(m);
        }
    }
    
  
}
