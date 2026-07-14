package uepa.aplicativo.controllers.CatalogScreen;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import uepa.aplicativo.loaders.CatalogCardLoader;
import uepa.aplicativo.extracurricular.Extracurricular;

public class CatalogController implements Initializable {

    @FXML
    private VBox catalog;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        List<HBox> card = cardInitialize();
        catalog.getChildren().clear();
        catalog.getChildren().add(card);
        System.out.println("Hello Catalog!");
    }

}

