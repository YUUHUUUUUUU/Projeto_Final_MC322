package uepa.aplicativo;

import javafx.application.Application;
import javafx.stage.Stage;
import uepa.aplicativo.DataManager.xmlWriter;
import uepa.aplicativo.SceneManager.SceneManager;
import uepa.aplicativo.loaders.IconLoader;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        //xmlWriter.writeUsers("/src/main/resources/xml/saida.xml");
        IconLoader.tryLoadingIcon(primaryStage);
        //String fxmlString = "/fxml/CatalogScreen/Catalog.fxml";
        String fxmlString = "/fxml/LoginScreen/LoginScreen.fxml";
        String pageTitle = "Login Screen";
        SceneManager.initializeFirstScene(primaryStage, fxmlString, pageTitle);
    }
}