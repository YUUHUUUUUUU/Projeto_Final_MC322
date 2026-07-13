package uepa.aplicativo.controllers.RegisterScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import uepa.aplicativo.user.UserManager;

public class RegisterController {

    @FXML
    private TextField completeNameField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    void Register(ActionEvent event) {
        String completeName = completeNameField.getText();
        String fullEmail = emailField.getText();
        String password = passwordField.getText();
        String confirmedPassword = confirmPasswordField.getText();
        try{
            UserManager.signIn(completeName, fullEmail, password, confirmedPassword);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

}