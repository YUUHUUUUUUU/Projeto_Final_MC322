package uepa.aplicativo.controllers.CatalogScreen;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import uepa.aplicativo.DataManager.Data;
import uepa.aplicativo.SceneManager.SceneManager;
import uepa.aplicativo.controllers.ExtracurricularScreen.ExtracurricularController;
import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.interfaces.RecieveData;
import uepa.aplicativo.user.Staff;

public class CardController implements RecieveData {
    
    Data data;

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
        RedirectToExtra(event);
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
            Image image = extracurricular.getLogo();
            setName(name);
            setDescription(description);
            setImage(image);
    }

    private void setName(String name) {
        this.name.setText(name);
    }
    private void setDescription(String description) {
        this.description.setText(description);
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

    @Override
    public void receiveData(Data data) {
        setData(data);
    }

    @Override
    public void receiveData(Data data, Extracurricular extra){
        receiveData(data);
    }

    @Override
    public void setData(Data data) {
        this.data = data;
    }

    void RedirectToExtra(ActionEvent event) {
        try{
            String fxmlPath = "/fxml/ExtracurricularScreen/ExtracurricularScreen.fxml";
            String pageTitle = extracurricular.getName() + " Screen";
            FXMLLoader fxmlLoader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
            System.out.println("URL carregada: " + fxmlLoader.getLocation());
            Parent root = fxmlLoader.load();

            ExtracurricularController controller = fxmlLoader.getController();

            controller.receiveData(data);

            Scene screen = new Scene(root);

            Node source = (Node) event.getSource();
            Scene currentScene = source.getScene();
            Stage stage = (Stage) currentScene.getWindow();

            root.requestFocus();
            stage.setTitle(pageTitle);
            stage.setScene(screen);
            stage.setResizable(true);
            stage.setMaximized(true);
            stage.show();
        }
        catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        
    }

}
