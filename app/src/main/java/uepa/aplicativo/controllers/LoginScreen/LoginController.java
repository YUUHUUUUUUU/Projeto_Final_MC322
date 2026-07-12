package uepa.aplicativo.controllers.LoginScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import uepa.aplicativo.user.UserManager;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    void LogIn(ActionEvent event) {
        try {
            String email = emailField.getText();
            String password = passwordField.getText();
            UserManager userManager = new UserManager();

            userManager.login(email, password);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

}
