package uepa.aplicativo.controllers.CatalogScreen;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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

    private List<HBox> loadCatalog() throws Exception{

        /* We create our empty catalog list */
        List<HBox> catalogItems = new ArrayList<>();
        try {
            /* Try loading the EXTRAS data base */
            List<Extracurricular> extracurricularList = new ArrayList<>();

            /* we load the card */
            if(!extracurricularList.isEmpty()){
                for(Extracurricular extra : extracurricularList) {

                    /* Load a single default card */
                    HBox card = CatalogCardLoader.loadCatalogCard();

                    /* Get the card controller */
                    CardController controller = CatalogCardLoader.getController();

                    /* Fill the card with the extracurricular data */
                    String name = extra.getName();
                    String description = extra.getDescription();
                    String initialEnrollmentDate = "DD/MM/AAAA HH:MM"; // need to add getters
                    String finalEnrollmentDate = "DD/MM/AAAA HH:MM";   // need to add getters
                    Image image = extra.getLogo();
                    controller.setData(name, description, initialEnrollmentDate, finalEnrollmentDate, image);
                }
            }
        }
        catch(Exception e) {
            throw new Exception("Failed to load cards", e);
        }


    }

}

