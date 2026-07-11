package uepa.aplicativo.controllers.LoginScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    void LogIn(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();
        System.out.println(email);
        System.out.println(password);
    }

}
