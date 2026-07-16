package uepa.aplicativo.extracurricular;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import uepa.aplicativo.extracurricular.gallery.PhotoGallery;
import uepa.aplicativo.message.Message;
import uepa.aplicativo.user.Staff;
import uepa.aplicativo.user.User;
import uepa.aplicativo.constants.*;
import uepa.aplicativo.interfaces.*;

public abstract class Extracurricular implements notify{
    private String name;
    private ArrayList<User> listeners;
    private boolean openToWork;
    private String description;
    private List<Staff> staffList;
    private PhotoGallery photoGallery;
    private boolean threeDaysNotification = false;
    private boolean sevenDaysNotification = false;
    private boolean oneDayNotification = false;
    private String institute;


    private String fxmlPath;

    public Extracurricular(String name, String description,
         String logoPath, String bannerPath, String institute, boolean openToWork,
          String hyperLink, List<Staff> staffs) {
        setName(name);
        setDescription(description);
        this.listeners = new ArrayList<>();

        photoGallery = new PhotoGallery("/logo/UEPA.png");
        staffList = new ArrayList<>();
    }

    public String getName(){
        return this.name;
    }

    public boolean setName(String n){
        this.name=n;
        return true;
    }

    public String getDescription(){
        return this.description;
    }

    public boolean setDescription(String d){
        this.description=d;
        return true;
    }

    public boolean setOpenToWork(boolean b){
        this.openToWork=b;
        return b;
    }

    public boolean getOpenToWork(){
        return openToWork;
    }

    public Image getLogo() {
        return photoGallery.getLogo();
    }

    public Image getBanner() {
        return null;
    }

    public String getFxmlPath() {
        return fxmlPath;
    }

    public void setFxmlPath(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

    public List<Staff> getStaffList() {
        return this.staffList;
    }

    public PhotoGallery getPhotoGallery() {
        return this.photoGallery;
    }

    public void setPhotoGallery(PhotoGallery photoGallery) {
        this.photoGallery = photoGallery;
    }

    public void addStaff(Staff staff) {
        if(staff != null){
            this.staffList.add(staff);
        }
    }

    public List<User> getUsersListeners(){
        return listeners;
    }

    public void addtoNotify(User s){
        listeners.add(s);
    }

    public void removefromNotify(User s){
        int t=listeners.size();
        int pos=0;
        for (int i=0;i<t;i++){
            if (listeners.get(i).getID()==s.getID()){
                pos=i;
            }
        }
        listeners.remove(pos);
    }

    public boolean isFollowedBy(User s){
        int t= listeners.size();
        for (int i=0;i<t;i++){
            if (listeners.get(i).getID()==s.getID()){
                return true;
            }
        }
        return false;
    }

    public void notifyListeners(String title, String text) {

        Message m = new Message(title, text, this, null);
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).receiveMessage(m);
        }
    }
}
