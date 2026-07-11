package uepa.aplicativo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LoginScreen/LoginScreen.fxml")); 
            Parent root = fxmlLoader.load();
            Scene tela = new Scene(root);

            primaryStage.setTitle("Tela de Login");
            primaryStage.setScene(tela);
            primaryStage.show();
        }
        catch (Exception e) {
            System.out.println("Erro1");
        }
    }
}