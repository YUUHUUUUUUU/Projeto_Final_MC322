package uepa.aplicativo.controllers.RegisterScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import uepa.aplicativo.DataManager.Data;
import uepa.aplicativo.SceneManager.SceneManager;
import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.interfaces.RecieveData;
import uepa.aplicativo.user.UserManager;

public class RegisterController implements RecieveData{

    @FXML
    private TextField completeNameField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField emailField;

    @FXML
    private Label errorLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;

    Data data;

    @FXML
    void Register(ActionEvent event) {
        String completeName = completeNameField.getText();
        String fullEmail = emailField.getText();
        String password = passwordField.getText();
        String confirmedPassword = confirmPasswordField.getText();
        try{
            UserManager.signIn(completeName, fullEmail, password, confirmedPassword, data);
        }
        catch(Exception e) {
            System.out.println(e);
            SceneManager.showErrorMessage(errorLabel, e);
        }
    }

    @Override
    public void receiveData(Data data){
        setData(data);
    }

    @Override
    public void receiveData(Data data, Extracurricular extracurricular) {
        receiveData(data);
    }

    @Override
    public void setData(Data data) {
        this.data = data;
    }
}