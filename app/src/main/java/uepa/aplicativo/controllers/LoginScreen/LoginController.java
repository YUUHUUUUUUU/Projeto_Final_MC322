package uepa.aplicativo.controllers.LoginScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import uepa.aplicativo.user.UserManager;
import uepa.aplicativo.DataManager.Data;
import uepa.aplicativo.SceneManager.SceneManager;
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
            UserManager.login(email, password);
        }
        catch (Exception e) {
            System.out.println(e);
            SceneManager.showErrorMessage(errorLabel, e);
        }
    }

    @FXML
    void RedirectToRegister(ActionEvent event) {
        String fxmlPath = "/fxml/RegisterScreen/RegisterScreen.fxml";
        String pageTitle = "Register Screen";
        SceneManager.switchScene(event, fxmlPath, pageTitle);
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
