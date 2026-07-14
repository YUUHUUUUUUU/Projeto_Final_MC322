package uepa.aplicativo.loaders;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

public class CatalogCardLoader {
     
    private CatalogCardLoader() {}

    public static HBox loadCatalogCard() {
        String fxmlPath = "/fxml/Card.fxml";
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(CatalogCardLoader.class.getResource(fxmlPath)); 
            HBox card = fxmlLoader.load();
            return card;
        }
        catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
            return null;
        }
        catch (ClassCastException e) {
            System.out.println(e);
            e.printStackTrace();
            return null;
        }
    }
}
