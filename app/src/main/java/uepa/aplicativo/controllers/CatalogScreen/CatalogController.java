package uepa.aplicativo.controllers.CatalogScreen;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import uepa.aplicativo.loaders.CatalogCardLoader;

public class CatalogController implements Initializable {

    @FXML
    private VBox catalog;

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

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        HBox card = CatalogCardLoader.loadCatalogCard();
        catalog.getChildren().clear();
        catalog.getChildren().add(card);
        System.out.println("Hello Catalog!");
    }

}

