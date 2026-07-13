package uepa.aplicativo.SceneManager;

import java.io.IOException;

import javax.swing.Action;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

    /* We dont want to instantiate  this class, only use its method */
    private SceneManager() {}

    /**
     * Represents the method of switching between scenes
     * 
     * <p>
     * This method is important because we encapsulate the scene switch process,
     * in other words, he hide the scene switch process from the from the Controllers.
     * </p>
     * 
     * @param event represent some type of action. This event type is widely used to represent
     *  a variety of things, such as when a javafx.scene.control.Button has been fired, when a
     *  javafx.animation.KeyFrame has finished, and other such usages.
     * @param fxmlPath represents the path to the fxml file relative to /app/src/main/resources/
     * @param pageTitle represents the title that will show on the top of the new page
     * 
     * @author Enzo Farina Mullis
     */
    public static void switchScene(ActionEvent event, String fxmlPath, String pageTitle) {
        try{
            /* We load the fxml */
            FXMLLoader fxmlLoader = new FXMLLoader(SceneManager.class.getResource(fxmlPath)); 
            /* We load the fxml into a Parent (the base for our scene graph) */
            Parent root = fxmlLoader.load();

            /* We create the container for the scene graph */
            Scene screen = new Scene(root);
            
            /* Casting is necessary here, because getSource() return is a Object type
             * and only Node type has the getSource() method
             */
            Node source = (Node) event.getSource();
            /* We get the currentScene that is loaded */ 
            Scene currentScene = source.getScene();
            /* And now we have to cast again because getWindow() return a Window type
             * and we are working generally with Stage
             */
            Stage stage = (Stage) currentScene.getWindow();

            /* We made a standart configuration */
            root.requestFocus();
            stage.setMaximized(true);
            stage.setTitle(pageTitle);
            stage.setScene(screen);
            stage.setResizable(true);
            stage.show();
        }
        catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        catch (ClassCastException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
