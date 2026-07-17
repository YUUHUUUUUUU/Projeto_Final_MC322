package uepa.aplicativo.controllers.RegisterScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
            RedirectToLogin(event);
        }
        catch(Exception e) {
            System.out.println(e);
            SceneManager.showErrorMessage(errorLabel, e);
        }
    }

    void RedirectToLogin(ActionEvent event) {
        try{
            Scene screen = SceneManager.goBackToLogin();

            Node source = (Node) event.getSource();
            Scene currentScene = source.getScene();
            Stage stage = (Stage) currentScene.getWindow();

            stage.setTitle("Catalog Screen");
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