package uepa.aplicativo.extracurricular;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import uepa.aplicativo.user.Staff;
import uepa.aplicativo.interfaces.notify;
import uepa.aplicativo.interfaces.Notificable;

public abstract class Extracurricular {
    private String name;
    private String description;
    private boolean openToWork;
    private String institute;
    private String bannerPath;
    private String logoPath;
    private String hyperLink;
    private String fxmlPath;
    private List<Staff> staffList = new ArrayList<>();
    private List<Notificable> usersListeners = new ArrayList<>();

    public Extracurricular(String name, String description, boolean openToWork, String institute,
        String bannerPath, String logoPath, String hyperLink, String fxmlPath) {
        setName(name);
        setDescription(description);
        setOpenToWork(openToWork);
        setInstitute(institute);
        setBannerPath(bannerPath);
        setLogoPath(logoPath);
        setHyperLink(hyperLink);
        setfxmlPath(fxmlPath);
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio.");
        }
        if (name.length() > 100) {
            throw new IllegalArgumentException("O nome não pode exceder 100 caracteres.");
        }
        this.name = name;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição não pode estar vazia.");
        }
        if (description.length() > 1000) {
            throw new IllegalArgumentException("Descrição muito longa (máximo de 1000 caracteres).");
        }
        this.description = description;
    }

    public void setOpenToWork(boolean openToWork) {
        this.openToWork = openToWork;
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

    public void setLogoPath(String logoPath) {
        if (logoPath == null || logoPath.trim().isEmpty()) {
            throw new IllegalArgumentException("O caminho da logo não pode ser nulo ou vazio.");
        }
        this.logoPath = logoPath;
    }

    public void setHyperLink(String hyperLink) {
        if (hyperLink == null || hyperLink.trim().isEmpty()) {
            throw new IllegalArgumentException("O link não pode ser nulo ou vazio.");
        }
        this.hyperLink = hyperLink;
    }

    public void addStaff(Staff staff) {
        if (staff == null) {
            throw new IllegalArgumentException("O membro da equipe não pode ser nulo.");
        }
        if (this.staffList.contains(staff)) {
            throw new IllegalArgumentException("Este membro já está cadastrado nesta extracurricular.");
        }
        this.staffList.add(staff);
    }

    public void removeStaff(Staff staff) {O culpado é 100% o Test Runner (a extensão de testes) do VS Code que resolveu fazer greve e não quer listar os testes na interface de jeito nenhum.
        if (staff == null) {
            throw new IllegalArgumentException("O membro da equipe não pode ser nulo.");
        }
        if (!this.staffList.contains(staff)) {
            throw new IllegalArgumentException("O membro não foi encontrado na lista desta extracurricular.");
        }
        this.staffList.remove(staff);
    }

    public void addListener(Notificable listener) {
        if (listener == null) {
            throw new IllegalArgumentException("O ouvinte de notificações não pode ser nulo.");
        }
        if (this.usersListeners.contains(listener)) {
            throw new IllegalArgumentException("Este usuário já está recebendo notificações.");
        }
        this.usersListeners.add(listener);
    }

    public void removeListener(Notificable listener) {
        if (listener == null) {
            throw new IllegalArgumentException("O ouvinte de notificações não pode ser nulo.");
        }
        if (!this.usersListeners.contains(listener)) {
            throw new IllegalArgumentException("O ouvinte não foi encontrado na lista de notificações.");
        }
        this.usersListeners.remove(listener);
    }

    public String getFxmlPath(){
        return fxmlPath;
    }

    public void setfxmlPath(String fxml){
        this.fxmlPath=fxml;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isOpenToWork() {
        return openToWork;
    }

    public String getInstitute() {
        return institute;
    }

    public String getBannerPath() {
        return bannerPath;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public String getHyperLink() {
        return hyperLink;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public List<Notificable> getUsersListeners() {
        return usersListeners;
    }

    public Image getLogo() {
    return new Image(getClass().getResourceAsStream(this.logoPath));
    }

    public Image getBanner() {
        return new Image(getClass().getResourceAsStream(this.bannerPath));
    }
}