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
public abstract class Extracurricular {
    private String name;
    private ArrayList<User> listners;
    private List<Tag> tags = new ArrayList<Tag>();
    private boolean openToWork;
    private String description;
    public Staff moderator;
    public boolean hasModerator;
    private ZonedDateTime timezone;
    private ZonedDateTime initialEnrollmentDate;
    private ZonedDateTime finalEnrollmentDate;
    private PhotoGallery photoGallery;
    private boolean threeDaysNotification = false;
    private boolean sevenDaysNotification = false;
    private boolean oneDayNotification = false;


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
    public List<Tag> getTags() {
        return this.tags;
    }
    public boolean addTag(Tag tag) {
        if (tag == null) {
            return false;
        }
        if (this.tags.contains(tag)) {
            return false; // Evita duplicatas
        }
        return this.tags.add(tag);
    }
    public boolean removeTag(Tag tag) {
        if (tag == null) {
            return false;
        }
        return this.tags.remove(tag);
    }
    public boolean hasTag(Tag tag) {
        return this.tags.contains(tag);
    }

    public Staff getModerator() {
        return this.moderator;
    }
    public void setModerator(Staff moderator) {
        this.moderator = moderator;
        this.hasModerator = (moderator != null); 
    }
    public boolean isHasModerator() {
        return this.hasModerator;
    }

    public ZonedDateTime getTimezone() {
        return this.timezone;
    }

    public void notifyListeners(Message m) {
        for (int i = 0; i < listners.size(); i++) {
            listners.get(i).receiveMessage(m);
        }
    }
    public void setTimezone(ZonedDateTime timezone) {
        this.timezone = timezone;
    }
    /*public ZonedDateTime getInitial() {
        return this.initialEnrollmentDate;
    }
    public void setInitialEnrollmentDate(ZonedDateTime initialEnrollmentDate) {
        this.initialEnrollmentDate = initialEnrollmentDate;
    }

    public ZonedDateTime getFinal() {
        return this.finalEnrollmentDate;
    }

    public void setFinalEnrollmentDate(ZonedDateTime finalEnrollmentDate) {
        this.finalEnrollmentDate = finalEnrollmentDate;
    }*/
    public PhotoGallery getPhotoGallery() {
        return this.photoGallery;
    }

    public void setPhotoGallery(PhotoGallery photoGallery) {
        this.photoGallery = photoGallery;
    }


    public long  DaysLeft(){
        if (finalEnrollmentDate == null) {
            System.out.println("Data final não definida");
            return 0;
        }
        ZonedDateTime now = ZonedDateTime.now(this.finalEnrollmentDate.getZone());
        long daysLeft = ChronoUnit.DAYS.between(now, this.finalEnrollmentDate);
        if (daysLeft < 0) {
            return  0;
        }
        return daysLeft;
    }
    public void updateStatus() {
        long daysLeft = DaysLeft();
        if (daysLeft >= 0) {
            this.openToWork = true;
        } else {
            this.openToWork = false;
        }
    }
    public void automaticReminder() {
        long daysLeft = DaysLeft();
        if (daysLeft <= 7 && sevenDaysNotification == false) {
            Message m7 = new Message("AVISO!","7 dias faltando para o encerramento",this, 
            this.moderator);
            this.notifyListeners(m7);
            this.sevenDaysNotification = true;
        } 
        else if (daysLeft <= 3 && threeDaysNotification == false) {
            Message m3 = new Message("AVISO!","3 dias faltando para o encerramento",this,
            this.moderator);
            this.notifyListeners(m3);
            this.threeDaysNotification = true;
        }
        else if (daysLeft <= 1 && oneDayNotification == false) {
            Message m1 = new Message("AVISO!"," Falta 1 dia para o encerramento",this,
            this.moderator);
            this.notifyListeners(m1);
            this.oneDayNotification = true;            
        }
    }

    
  
}
