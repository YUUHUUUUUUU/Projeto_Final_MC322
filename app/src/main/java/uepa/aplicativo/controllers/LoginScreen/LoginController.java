package uepa.aplicativo.controllers.LoginScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import uepa.aplicativo.user.UserManager;
import uepa.aplicativo.DataManager.Data;
import uepa.aplicativo.SceneManager.SceneManager;
import uepa.aplicativo.controllers.RegisterScreen.RegisterController;
import uepa.aplicativo.interfaces.RecieveData;

public class LoginController implements RecieveData{

    @FXML
    private TextField emailField;

    @FXML
    private Label errorLabel;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    private Data data;

    @FXML
    void LogIn(ActionEvent event) {
        try {
            String email = emailField.getText();
            String password = passwordField.getText();
            UserManager.login(email, password, data);
        }
        catch (Exception e) {
            System.out.println(e);
            SceneManager.showErrorMessage(errorLabel, e);
        }
    }

    @FXML
    void RedirectToRegister(ActionEvent event) {
        try{
            String fxmlPath = "/fxml/RegisterScreen/RegisterScreen.fxml";
            String pageTitle = "Register Screen";
            FXMLLoader fxmlLoader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
            Parent root = fxmlLoader.load();
            RegisterController controller = fxmlLoader.getController();

            controller.receiveData(data);

            Scene screen = new Scene(root);

            Node source = (Node) event.getSource();
            Scene currentScene = source.getScene();
            Stage stage = (Stage) currentScene.getWindow();

            root.requestFocus();
            stage.setTitle(pageTitle);
            stage.setScene(screen);
            stage.setResizable(true);
            stage.setMaximized(true);
            stage.show();
        }
        catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        
    }

    @Override
    public void receiveData(Data data) {
        setData(data);
    }

    @Override
    public void setData(Data data) {
        this.data = data;
    }

}
