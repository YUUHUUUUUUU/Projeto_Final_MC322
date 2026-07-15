package uepa.aplicativo.controllers.CatalogScreen;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.extracurricular.StudentAssociation;
import uepa.aplicativo.loaders.CatalogCardLoader;
import uepa.aplicativo.user.Staff;

public class CardController {
    
    @FXML
    private HBox card;

    @FXML
    private ImageView image;

    @FXML
    private Label name;

    @FXML
    private Label description;

    @FXML
    private Label initialEnrollmentDate;

    @FXML
    private Label finalEnrollmentDate;

    @FXML
    private Label type;

    @FXML
    private Label institute;

    @FXML
    private Button seeMoreButton;

    @FXML
    void clickedSeeMore(ActionEvent event) {
        System.out.println("Hello See More Button");
        System.out.println(extracurricular.getFxmlPath());
    }

    private Extracurricular extracurricular;
    private Staff staff;

    /**
     * Represents the method that applies the extracurricular data
     * to the Card
     */
    private void applyData() {
            String name = extracurricular.getName();
            String description = extracurricular.getDescription();
            String initialEnrollmentDate = "DD/MM/AAAA HH:MM";
            String finalEnrollmentDate = "DD/MM//AAAA HH:MM";
            Image image = extracurricular.getLogo();
            setName(name);
            setDescription(description);
            setInitialEnrollmentDate(initialEnrollmentDate);
            setFinalEnrollmentDate(finalEnrollmentDate);
            setImage(image);
    }

    private void setName(String name) {
        this.name.setText(name);
    }
    private void setDescription(String description) {
        this.description.setText(description);
    }
    private void setInitialEnrollmentDate(String initialEnrollmentDate) {
        this.initialEnrollmentDate.setText(initialEnrollmentDate);
    }
    private void setFinalEnrollmentDate(String finalEnrollmentDate) {
        this.finalEnrollmentDate.setText(finalEnrollmentDate);
    }
    private void setImage(Image image) {
        this.image.setImage(image);
    }


    /**
     * Represents the setter for the card Extracurricular
     * 
     * @param extra a Extracurricular
     */
    private void setExtracurricular(Extracurricular extra) {
        extracurricular = extra;
    }

    public void printExtra(){
        System.out.println("Extracurricular successfully loaded: " + extracurricular.getName());
    }

    /**
     * Represents the fully encapsulation of the methods that loads the
     * extracurricular data into the card
     * 
     * <p>
     * This is important, because we hide the real implementation of
     * the CatalogController, so the CatalogController just needs to
     * call the method and not handle with the implementation
     */
    public void loadExtracurricular(Extracurricular extra) {
        setExtracurricular(extra);
        applyData();
        printExtra();
    }

    public void loadStaff(Staff s) {
        setStaff(s);
        applyStaffData();
    }

    private void setStaff(Staff staff) {
        this.staff = staff;
    }

    private void applyStaffData(){
        String name = staff.getName();
        String description = staff.getEmail();
        Image image = staff.getPhoto();
        setName(name);
        setDescription(description);
        setImage(image);
    }

    

}
