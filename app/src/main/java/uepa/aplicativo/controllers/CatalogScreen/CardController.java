package uepa.aplicativo.controllers.CatalogScreen;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class CardController {
    
    @FXML
    private HBox card;

    @FXML
    private ImageView cardImage;

    @FXML
    private Label cardName;

    @FXML
    private Label cardDescription;

    @FXML
    private Label cardInitialEnrollmentDate;

    @FXML
    private Label cardFinalEnrollmentDate;

    public void setData(String name, String description,
         String initialEnrollmenteDate, String finalEnrollmentDate) {

    }

    private void setName() {

    }
    private void setDescription() {}
    private void setInitialEnrollmentDate() {}
    private void setFinalEnrollmentDate() {}
    private void setImage() {}
}
