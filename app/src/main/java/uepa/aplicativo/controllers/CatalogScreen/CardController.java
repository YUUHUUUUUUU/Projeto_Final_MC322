package uepa.aplicativo.controllers.CatalogScreen;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.loaders.CatalogCardLoader;

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

    public void setData(String name, String description,
         String initialEnrollmentDate, String finalEnrollmentDate, Image image) {
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

}
