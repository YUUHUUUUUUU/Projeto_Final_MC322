package uepa.aplicativo.loaders;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import uepa.aplicativo.controllers.CatalogScreen.CardController;
import uepa.aplicativo.loaders.loadedData.LoadedCard;

public class CatalogCardLoader {
     
    private CatalogCardLoader() {}

    public static LoadedCard loadCatalogCard() throws Exception{

        String fxmlPath = "/fxml/CatalogScreen/Card.fxml";
        return loadCard(fxmlPath);
    }

    public static LoadedCard loadStaffCard() throws Exception {
        String fxmlPath = "/fxml/CatalogScreen/StaffCard.fxml";
        return loadCard(fxmlPath);
    }

    public static LoadedCard loadCard(String fxmlPath) throws Exception{

        /* We will need to save the arguments of two parameters
         * the first one is the HBox of the card,
         * and the other one is its Controller (CardController).
         * This will all be saved in LoadedCard, a class created
         * just to save these arguments.
         * 
         * The HBox we will use to add the "card" to the catalog (VBox)
         * and the Controller to edit the information for each "card".
         */
        LoadedCard loadedCard;
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(CatalogCardLoader.class.getResource(fxmlPath)); 
            HBox box = fxmlLoader.load();
            CardController controller = fxmlLoader.getController();
            loadedCard = new LoadedCard(box, controller);
            return loadedCard;
        }
        catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
            throw e;
        }
        catch (ClassCastException e) {
            System.out.println(e);
            e.printStackTrace();
            throw e;
        }
    }
}
