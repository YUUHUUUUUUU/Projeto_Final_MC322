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
        List<HBox> card = cardInitialize();
        catalog.getChildren().clear();
        catalog.getChildren().add(card);
        System.out.println("Hello Catalog!");
    }

    private List<HBox> cardInitialize() throws Exception{

        /* We create our empty catalog list */
        List<HBox> catalogItems = new ArrayList<>();
        try {
            /* Try loading the EXTRAS data base */
            List<Extracurricular> extracurricularList = new ArrayList<>();

            /* we load the card */
            for(Extracurricular exta : extracurricularList) {

                /* Load a single default card */
                HBox card = CatalogCardLoader.loadCatalogCard();

                /* Fill the card */
            }


        }
        catch(Exception e) {
            throw new Exception("Failed to load cards", e);
        }


    }

    private void cardSetName() {}
    private void cardSetDescription() {}
    private void cardSetInitialEnrollmentDate() {}
    private void cardSetFinalEnrollmentDate() {}
    private void cardSetImage() {}
}

