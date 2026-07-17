package uepa.aplicativo.controllers.CatalogScreen;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import uepa.aplicativo.loaders.CatalogCardLoader;
import uepa.aplicativo.loaders.loadedData.LoadedCard;
import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.extracurricular.StudentAssociation;

public class CatalogController implements Initializable {

    @FXML
    private VBox catalog;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        try {
            /* We load the list of cards */
            List<HBox> catalogItems = loadCatalog();

            /* We clear and add each item to the catalog */
            catalog.getChildren().clear();

            for(HBox card : catalogItems) {
                catalog.getChildren().add(card);
            }
            System.out.println("Hello Catalog!");
        }
        catch(Exception e) {
            System.out.println("Failed to load the Catalog");
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
        
    }

    @FXML
        void mailBoxClicked(ActionEvent event) {

    }


    private List<HBox> loadCatalog() throws Exception{

        /* We create our empty catalog list */
        List<HBox> catalogItems = new ArrayList<>();
        try {
            /* Try loading the EXTRAS data base */
            // we will probably use a static method for this!
            // maybe on initialize
            List<Extracurricular> extracurricularList = new ArrayList<>();

            /* JUST FOR TESTING: we will be adding manually some extras */
            Extracurricular extra1 = new StudentAssociation("Extra1", "Desc1", true, "IFGW", "/logo/UEPA.png", "/logo/UEPA.png", "google.com","path");
            Extracurricular extra2 = new StudentAssociation("Extra1", "Desc1", true, "IFGW", "/logo/UEPA.png", "/logo/UEPA.png", "google.com","path");

            extracurricularList.add(extra1);
            extracurricularList.add(extra2);

            /* we load the card */
            if(!extracurricularList.isEmpty()){
                for(Extracurricular extra : extracurricularList) {

                    /* Load a single default card */
                    LoadedCard loadedCard = CatalogCardLoader.loadCatalogCard();

                    /* Get the card controller */
                    CardController controller = loadedCard.getController();
                    HBox card = loadedCard.getRoot();

                    if(controller == null) {
                        throw new Exception("Controller can not be null");
                    }
                    if (card == null) {
                        throw new Exception("Card can not be null");
                    }
                    /* Load the extracurricular into the card */
                    controller.loadExtracurricular(extra);

                    /* add the card to the list */
                    catalogItems.add(card);
                }
            }
        }
        catch(Exception e) {
            throw new Exception("Failed to load cards", e);
        }

        if(catalogItems.isEmpty()){
            throw new Exception("Catalog is empty");
        }

        return catalogItems;

    }

}

