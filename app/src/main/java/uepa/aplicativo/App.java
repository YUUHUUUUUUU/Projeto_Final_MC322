package uepa.aplicativo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
 

import uepa.aplicativo.loaders.*;


public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        FontLoader fontLoader = new FontLoader();
        fontLoader.loadFonts();

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/RegisterScreen/RegisterScreen.fxml")); 
            Parent root = fxmlLoader.load();
            Scene tela = new Scene(root);
            
            root.requestFocus();

            primaryStage.setMaximized(true);
            primaryStage.setTitle("Tela de Login");
            primaryStage.setScene(tela);
            primaryStage.setResizable(true);
            primaryStage.show();
        }
        catch (Exception e) {
            System.out.println("Erro1");
        }
    }

    
}