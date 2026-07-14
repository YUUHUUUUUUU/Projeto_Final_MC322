package uepa.aplicativo.loaders;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import uepa.aplicativo.controllers.CatalogScreen.CardController;

public class CatalogCardLoader {
     
    private CatalogCardLoader() {}

    public static HBox loadCatalogCard() throws Exception{
        String fxmlPath = "/fxml/CatalogScreen/Card.fxml";
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(CatalogCardLoader.class.getResource(fxmlPath)); 
            HBox card = fxmlLoader.load();
            return card;
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

    public static CardController getController() {
        String fxmlPath = "/fxml/CatalogScreen/Card.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(CatalogCardLoader.class.getResource(fxmlPath));
        return fxmlLoader.getController();
    }
}
