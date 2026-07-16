package uepa.aplicativo.controllers.PopupScreen;

import com.google.common.base.Optional;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class PopupController {

    public static Optional<String> showInputPopup(Stage owner, String title, String fxmlPath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PopupController.class.getResource(fxmlPath)); 
            Parent root = fxmlLoader.load();
            Scene screen = new Scene(root);
            Stage popupStage = new Stage();

            /* We want to block the application behind */
            popupStage.initModality(Modality.APPLICATION_MODAL);

            /* We define the owner of the popup */
            popupStage.initOwner(owner);

            popupStage.setTitle(title);
        }
        catch (Exception e) {
            
        }
    }
}
