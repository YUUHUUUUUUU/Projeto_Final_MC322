package uepa.aplicativo.controllers.CatalogScreen;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class CatalogController implements Initializable {

    @FXML
    private VBox catalog;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Hello Catalog!");
    }

}

