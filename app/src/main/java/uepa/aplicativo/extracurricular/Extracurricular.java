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
    
    //gerais:
    private String name;
    private boolean openToWork;
    private String description;
    private String institute;

    //listas:
    private ArrayList<User> listeners;
    private List<Staff> staffList;

    //fotos:
    private PhotoGallery photoGallery;
    private String bannerPath;
    private String logoPath;

    //xml:
    private String fxmlPath;

    //links:
    private String hyperlink;

    public Extracurricular(String name, String description, boolean openToWork,  String institute,
         String logoPath, String bannerPath,
          String hyperLink, String fxmlPath) {
        
        setName(name);
        setDescription(description);
        setOpenToWork(openToWork);
        setInstitute(institute);
        
        setHyperLink(hyperlink);

        setBannerPath(bannerPath);
        setLogoPath(logoPath);

        setFxmlPath(fxmlPath);
    
        listeners = new ArrayList<>();
        photoGallery = new PhotoGallery(logoPath);
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

    public boolean getOpenToWork(){
        return openToWork;
    }

    public boolean setOpenToWork(boolean b){
        this.openToWork=b;
        return b;
    }

    public String getInstitute(){
        return institute;
    }

    public void setInstitute(String institute){
        if (institute==null){
            System.err.println("Erro!");
        } else {
            boolean b=true;
            for(int i=0;i<institute.length();i++){
                char c=institute.charAt(i);
                int t=Character.getNumericValue(c);
                if ((t>=65 && t<=90) || (t>=97 && t<=122)){
                    continue;
                } else {
                    b=false;
                    break;
                }
            }
            if (b){
                this.institute=institute;
            }
        }
    }

    public String getHyperLink(){
        return hyperlink;
    }

    public void setHyperLink(String hyperlink){
        this.hyperlink=hyperlink;
    }

    public Image getLogo() {
        return photoGallery.getLogo();
    }

    public void setLogoPath(String logoPath){
        this.logoPath=logoPath;
    }

    public Image getBanner() {
        return null;
    }

    public void setBannerPath(String bannerPath){
        this.bannerPath=bannerPath;
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

    @Override
    public void notifyListeners(String title, String text) {

        Message m = new Message(title, text, this, null);
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).receiveMessage(m);
        }
    }
}
