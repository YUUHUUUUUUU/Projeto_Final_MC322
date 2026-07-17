package uepa.aplicativo.extracurricular;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import uepa.aplicativo.user.Staff;
import uepa.aplicativo.user.User;
import uepa.aplicativo.interfaces.notify;
import uepa.aplicativo.message.Message;

public class Extracurricular implements notify{
    
    private String name;
    private String description;
    private boolean openToWork;
    private String institute;
    private String isOpenString;
    private String fxmlPath;
    private String hyperlink;

    private List<User> listeners = new ArrayList<>();
    private List<Staff> staffList = new ArrayList<>();


    private List<Integer> listenersId = new ArrayList<>();
    private List<Integer> staffsId = new ArrayList<>();

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
    public Extracurricular(String name, String description, boolean openToWork,  String institute,
         String logoPath, String bannerPath,
          String hyperlink, String fxmlPath) {
        
        id = nextId++;

        setName(name);
        setDescription(description);
        setOpenToWork(openToWork);
        setInstitute(institute);
        setHyperLink(hyperlink);
        setBannerPath(bannerPath);
        setLogoPath(logoPath);

        setFxmlPath(fxmlPath);

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
          String hyperlink, String fxmlPath, 
          List<String> staffsIds, List<String> listenersIds, String idString) {

        /* converts the idString back to int */
        this.id = Integer.parseInt(idString);
        updateNextId(id);

        
        setName(name);
        setDescription(description);
        setOpenToWork(isOpenString);
        setInstitute(institute);
        setHyperLink(hyperlink);
        setBannerPath(bannerPath);
        setLogoPath(logoPath);
        setFxmlPath(fxmlPath);

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

    public boolean getOpenToWork(){
        return openToWork;
    }

    public void setOpenToWork(String isOpenString){
        this.isOpenString = isOpenString;
        if(isOpenString.equals("true")){
            this.openToWork=true;
        }
        else if(isOpenString.equals("false")){
            this.openToWork=false;
        }
    }

    public String getInstitute(){
        return institute;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição não pode estar vazia.");
        }
        if (description.length() > 300) {
            throw new IllegalArgumentException("Descrição muito longa (máximo de 1000 caracteres).");
        }
        this.description = description;
    }

    public void setOpenToWork(boolean openToWork) {
        this.openToWork = openToWork;
        if(this.openToWork) {
            this.isOpenString = "true";
        }
        else {
            this.isOpenString = "false";
        }
    }

    public void setInstitute(String institute) {
        if (institute == null || institute.trim().isEmpty()) {
            throw new IllegalArgumentException("O instituto não pode ser nulo ou vazio.");
        }
        this.institute = institute;
    }

    public void setBannerPath(String bannerPath) {
        if (bannerPath == null || bannerPath.trim().isEmpty()) {
            throw new IllegalArgumentException("O caminho do banner não pode ser nulo ou vazio.");
        }
        this.bannerPath = bannerPath;
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

    public void addStaff(Staff staff) {
        if (staff == null) {
            throw new IllegalArgumentException("O membro da equipe não pode ser nulo.");
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

    public void addListener(User listener) {
        if (listener == null) {
            throw new IllegalArgumentException("O ouvinte de notificações não pode ser nulo.");
        }
        if (this.listeners.contains(listener)) {
            throw new IllegalArgumentException("Este usuário já está recebendo notificações.");
        }
        this.listeners.add(listener);
    }

    public void removeListener(User listener) {
        if (listener == null) {
            throw new IllegalArgumentException("O ouvinte de notificações não pode ser nulo.");
        }
        if (!this.listeners.contains(listener)) {
            throw new IllegalArgumentException("O ouvinte não foi encontrado na lista de notificações.");
        }
        this.listeners.remove(listener);
    }

    public void setfxmlPath(String fxml){
        this.fxmlPath=fxml;
    }

    public boolean isOpenToWork() {
        return openToWork;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public String getHyperLink() {
        return this.hyperlink;
    }

    public void setLogoPath(String logoPath) {
        if(logoPath != null) {
            this.logoPath = logoPath;
        }
    }

    public Image getLogo() {
    return new Image(getClass().getResourceAsStream(this.logoPath));
    }

    public Image getBanner() {
        return new Image(getClass().getResourceAsStream(this.bannerPath));
    }

    public void setHyperLink(String link) {
        if(link != null) {
            this.hyperlink = link;
        }
    }
}