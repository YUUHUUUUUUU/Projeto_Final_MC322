package uepa.aplicativo.controllers.CatalogScreen;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

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

    public void setData(String name, String description,
         String initialEnrollmenteDate, String finalEnrollmentDate) {

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
        this.setFinalEnrollmentDate(finalEnrollmentDate);
    }
    private void setImage() {}
}
