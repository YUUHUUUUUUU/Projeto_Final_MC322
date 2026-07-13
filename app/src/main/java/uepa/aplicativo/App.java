package uepa.aplicativo;

import java.io.IOException;

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
        FontLoader.loadFonts();

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/LoginScreen/LoginScreen.fxml")); 
            Parent root = fxmlLoader.load();
            Scene tela = new Scene(root);
            
            root.requestFocus();

            primaryStage.setMaximized(true);
            primaryStage.setTitle("Tela de Login");
            primaryStage.setScene(tela);
            primaryStage.setResizable(true);
            primaryStage.show();
        }
        catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    
}