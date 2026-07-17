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

/**
 * Controlador JavaFX vinculado à tela de registro de novas contas (RegisterScreen).
 * Implementa a interface RecieveData para compartilhamento e sincronização da base de dados global.
 * * @author União de Entidades, Projetos e Atividades
 */
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

    /**
     * Trata o evento de clique do botão de cadastro. Extrai as strings dos formulários e invoca
     * as validações lógicas contidas no UserManager. Redireciona para o login caso obtenha sucesso.
     * * @param event Ação disparada pelo clique do botão.
     */
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

    /**
     * Altera de forma síncrona o contexto gráfico reencaminhando a janela para a tela inicial de Login.
     * * @param event Evento contendo o nó gráfico ativo para resgatar a janela principal (Stage).
     */
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

    /**
     * Recebe e configura a instância do hub de dados unificado.
     * * @param data Central de armazenamento em memória.
     */
    @Override
    public void receiveData(Data data){
        setData(data);
    }

    /**
     * Método polimórfico de contrato de dados que ignora o recebimento de contexto extracurricular específico.
     * * @param data Central de dados.
     * @param extracurricular Contexto descartado nesta tela.
     */
    @Override
    public void receiveData(Data data, Extracurricular extracurricular) {
        receiveData(data);
    }

    /**
     * Injeta e atualiza a propriedade contendo a referência da base de dados global do controlador.
     * * @param data Central de dados em memória.
     */
    @Override
    public void setData(Data data) {
        this.data = data;
    }
}