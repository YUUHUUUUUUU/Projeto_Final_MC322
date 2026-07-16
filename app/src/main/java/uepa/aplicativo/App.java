package uepa.aplicativo;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import uepa.aplicativo.DataManager.Data;
import uepa.aplicativo.DataManager.xmlReader;
import uepa.aplicativo.DataManager.xmlWriter;
import uepa.aplicativo.SceneManager.SceneManager;
import uepa.aplicativo.loaders.IconLoader;
import uepa.aplicativo.message.Message;
import uepa.aplicativo.user.Staff;
import uepa.aplicativo.user.Student;
import uepa.aplicativo.user.User;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {

        Data data = Data.loadData();
        Data.writeData(data);
        IconLoader.tryLoadingIcon(primaryStage);
        //String fxmlString = "/fxml/CatalogScreen/Catalog.fxml";
        String fxmlString = "/fxml/ExtracurricularScreen/ExtracurricularScreen.fxml";
        String pageTitle = "Login Screen";
        SceneManager.initializeFirstScene(primaryStage, fxmlString, pageTitle);
    }
}