package uepa.aplicativo;

import javafx.application.Application;
import javafx.stage.Stage;
import uepa.aplicativo.DataManager.Data;
import uepa.aplicativo.DataManager.xmlWriter;
import uepa.aplicativo.SceneManager.SceneManager;
import uepa.aplicativo.loaders.IconLoader;
import uepa.aplicativo.user.User;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        Data data = new Data();
        data.addUser(new User("email@email.com", "nome", "caminhoFoto"));
        xmlWriter.writeUsers("/src/main/resources/xml/saida.xml", data);
        IconLoader.tryLoadingIcon(primaryStage);
        //String fxmlString = "/fxml/CatalogScreen/Catalog.fxml";
        String fxmlString = "/fxml/ExtracurricularScreen/ExtracurricularScreen.fxml";
        String pageTitle = "Login Screen";
        SceneManager.initializeFirstScene(primaryStage, fxmlString, pageTitle);
    }
}