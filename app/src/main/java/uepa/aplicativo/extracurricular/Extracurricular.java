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
    private boolean openToWork;
    private String description;
    private String institute;

    private ArrayList<User> listeners = new ArrayList<>();
    private List<Staff> staffList = new ArrayList<>();

    private List<int[]> listenersId = new ArrayList<>();
    private List<int[]> staffsId = new ArrayList<>();

    private PhotoGallery photoGallery;
    private String bannerPath;
    private String logoPath;

    private String fxmlPath;

    private String hyperlink;

    private static int nextId = 0;
    private final int id;

    /**
     * Represents the constructor the should be called when a new Extra is created.
     * This construct doesn't read a id. Because a new Extra doesn't have an id yet.
     *
     * @param name
     * @param description
     * @param isOpen
     * @param institute
     * @param logoPath
     * @param bannerPath
     * @param hyperLink
     * @param fxmlPath
     * @param staffsIds
     * @param listenersIds
     */
    public Extracurricular(String name, String description, String isOpen,  String institute,
         String logoPath, String bannerPath,
          String hyperLink, String fxmlPath, List<int[]> staffsIds, List<int[]> listenersIds) {
        
        id = nextId++;

        setName(name);
        setDescription(description);
        setOpenToWork(isOpen);
        setInstitute(institute);
        
        setHyperLink(hyperlink);

        setBannerPath(bannerPath);
        setLogoPath(logoPath);

        setFxmlPath(fxmlPath);
    
        photoGallery = new PhotoGallery(logoPath);
        
        this.listenersId = listenersIds;
        this.staffsId = staffsIds;
    }

    /**
     * Represents the constructor to reconstruct the Extracurricular from XML.
     * This construct gets an id, to update the available ids for the registers.
     * This should be called only during the start of the application.
     * 
     * <p>
     * This Overflow is important because we need to guarantee that
     * every new register will come with a new id based on those saved on
     * the XML file
     * </p>
     * 
     * 
     * @param name
     * @param description
     * @param isOpen
     * @param institute
     * @param logoPath
     * @param bannerPath
     * @param hyperLink
     * @param fxmlPath
     * @param staffsIds
     * @param listenersIds
     * @param idString
     */
    public Extracurricular(String name, String description, String isOpen,  String institute,
         String logoPath, String bannerPath,
          String hyperLink, String fxmlPath, 
          List<int[]> staffsIds, List<int[]> listenersIds, String idString) {

        /* converts the idString back to int */
        this.id = Integer.parseInt(idString);
        updateNextId(id);


        setName(name);
        setDescription(description);
        setOpenToWork(isOpen);
        setInstitute(institute);
        setHyperLink(hyperlink);
        setBannerPath(bannerPath);
        setLogoPath(logoPath);
        setFxmlPath(fxmlPath);
        photoGallery = new PhotoGallery(logoPath);

        this.listenersId = listenersIds;
        this.staffsId = staffsIds;
    }

    /**
     * Represents the method to update the next id after loading the user data
     * @param existingId
     */
    public void updateNextId(int existingId) {
        if(existingId >= nextId) {
            nextId = existingId + 1;
        }
    }

    public String getIdString() {
        return Integer.toString(id);
    }

    public int getId() {
        return this.id;
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

    public void setOpenToWork(String open){
        if(open.equals("true")){
            this.openToWork=true;
        }
        else if(open.equals("false")){
            this.openToWork=false;
        }
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

    public String getLogoPath() {
        return logoPath;
    }

    public void setBannerPath(String bannerPath){
        this.bannerPath=bannerPath;
    }

    public String getBannerPath() {
        return bannerPath;
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

    public List<int[]> getListenersIds() {
        return listenersId;
    }

    public List<int[]> getStaffsIds() {
        return staffsId;
    }

    public void addtoNotify(User s){
        listeners.add(s);
    }

    @Override
    public void notifyListeners(String title, String text) {

        Message m = new Message(title, text, getName());
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).receiveMessage(m);
        }
    }
}
