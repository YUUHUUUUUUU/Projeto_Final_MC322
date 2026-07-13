package uepa.aplicativo;

import javafx.application.Application;
import javafx.stage.Stage;
import uepa.aplicativo.SceneManager.SceneManager;


public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        String fxmlString = "/fxml/LoginScreen/LoginScreen.fxml";
        String pageTitle = "Login Screen";
        SceneManager.initializeFirstScene(primaryStage, fxmlString, pageTitle);
    }
}