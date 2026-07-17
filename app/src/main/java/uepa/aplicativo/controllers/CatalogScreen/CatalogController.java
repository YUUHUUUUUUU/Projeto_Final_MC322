package uepa.aplicativo.controllers.CatalogScreen;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import uepa.aplicativo.loaders.CatalogCardLoader;
import uepa.aplicativo.loaders.loadedData.LoadedCard;
import uepa.aplicativo.DataManager.Data;
import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.interfaces.RecieveData;

public class CatalogController implements RecieveData {

    @FXML
    private VBox catalog;

    @FXML
    private Button mailBoxButton;

    @FXML
    void mailBoxClicked(ActionEvent event) {

    }

    public Data data;

    private List<HBox> loadCatalog() throws Exception{

        /* We create our empty catalog list */
        List<HBox> catalogItems = new ArrayList<>();
        try {

            /* We get the extras list from database */
            List<Extracurricular> extracurricularList = data.getExtracurricularList();

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

    @Override
    public void receiveData(Data data) {
        setData(data);
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

    @Override
    public void receiveData(Data data, Extracurricular extracurricular) {
        receiveData(data);
    }

    @Override
    public void setData(Data data) {
        this.data = data;
    }
}

