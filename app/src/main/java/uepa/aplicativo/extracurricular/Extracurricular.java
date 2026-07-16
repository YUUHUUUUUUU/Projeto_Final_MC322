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

public class Extracurricular implements notify{
    
    private String name;
    private String description;
    private String institute;
    private String isOpenString;
    private String fxmlPath;
    private String hyperlink;


    private boolean openToWork;

    private ArrayList<User> listeners = new ArrayList<>();
    private List<Staff> staffList = new ArrayList<>();

    private List<Integer> listenersId = new ArrayList<>();
    private List<Integer> staffsId = new ArrayList<>();

    private PhotoGallery photoGallery;
    private String bannerPath;
    private String logoPath;


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
    public Extracurricular(String name, String description, String isOpenString,  String institute,
         String logoPath, String bannerPath,
          String hyperLink, String fxmlPath, 
          List<String> staffsIds, List<String> listenersIds) {
        
        id = nextId++;

        setName(name);
        setDescription(description);
        this.isOpenString = isOpenString;
        setOpenToWork(isOpenString);
        setInstitute(institute);
        
        setHyperLink(hyperlink);

        setBannerPath(bannerPath);
        setLogoPath(logoPath);

        setFxmlPath(fxmlPath);
    
        photoGallery = new PhotoGallery(logoPath);
        
        setListenerList(listenersIds);
        setStaffList(staffsIds);
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
    public Extracurricular(String name, String description, String isOpenString,  String institute,
         String logoPath, String bannerPath,
          String hyperLink, String fxmlPath, 
          List<String> staffsIds, List<String> listenersIds, String idString) {

        /* converts the idString back to int */
        this.id = Integer.parseInt(idString);
        updateNextId(id);

        
        setName(name);
        setDescription(description);
        this.isOpenString = isOpenString;
        setOpenToWork(isOpenString);
        setInstitute(institute);
        
        setHyperLink(hyperlink);

        setBannerPath(bannerPath);
        setLogoPath(logoPath);

        setFxmlPath(fxmlPath);
    
        photoGallery = new PhotoGallery(logoPath);
        
        setListenerList(listenersIds);
        setStaffList(staffsIds);
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

    public void setStaffList(List<String> staffIds) {
        for(String s : staffIds) {
            this.staffsId.add(Integer.parseInt(s));
        }
    }

    public void setListenerList(List<String> listenerIds){
        for(String s : listenerIds) {
            this.listenersId.add(Integer.parseInt(s));
        }
    }

    public String getIsOpenString(){
        return this.isOpenString;
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

    public List<Integer> getListenersIds() {
        return listenersId;
    }

    public List<Integer> getStaffsIds() {
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
