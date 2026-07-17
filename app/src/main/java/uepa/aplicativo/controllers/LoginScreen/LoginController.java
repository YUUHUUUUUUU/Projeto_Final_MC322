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
import uepa.aplicativo.user.User;
import uepa.aplicativo.user.UserManager;
import uepa.aplicativo.DataManager.Data;
import uepa.aplicativo.SceneManager.SceneManager;
import uepa.aplicativo.constants.Role;
import uepa.aplicativo.controllers.CatalogScreen.CatalogController;
import uepa.aplicativo.controllers.RegisterScreen.RegisterController;
import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.interfaces.RecieveData;

/**
 * Controlador de eventos JavaFX acoplado à tela inicial de Login do ecossistema UEPA.
 * Valida logins de usuários, gerencia falhas em tempo de execução de credenciais e redireciona fluxos.
 * * @author União de Entidades, Projetos e Atividades
 */
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

    /**
     * Processa a submissão de credenciais. Caso o login seja validado, injeta o objeto do usuário 
     * como usuário logado na sessão ativa e roteia a interface rumo ao Catálogo Geral.
     * * @param event Ação contendo o botão disparado.
     */
    @FXML
    void LogIn(ActionEvent event) {
        try {
            String email = emailField.getText();
            String password = passwordField.getText();
            User loggedUser = UserManager.login(email, password, data);
            data.setLoggedUser(loggedUser);

            if(loggedUser.getRole().equals(Role.STUDENT)) {
                RedirectToCatalog(event);
            }

        }
        catch (Exception e) {
            System.out.println(e);
            SceneManager.showErrorMessage(errorLabel, e);
        }
    }

    /**
     * Altera o contexto gráfico da janela redirecionando o estudante para o formulário de cadastros, 
     * injetando a base Data de forma síncrona através do controlador da nova tela.
     * * @param event Ação originada do hiperlink/botão de novos cadastros.
     */
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
            SceneManager.setGoBackLogin(currentScene);
            stage.show();
        }
        catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        
    }

    /**
     * Intercepta e sincroniza a instância de dados em memória do escopo gráfico.
     * * @param data Instância global de dados.
     */
    @Override
    public void receiveData(Data data) {
        setData(data);
    }

    /**
     * Método polimórfico de interface que ignora contextos secundários de extracurriculares.
     * * @param data Hub de dados.
     * @param extracurricular Contexto ignorado nesta janela.
     */
    @Override
    public void receiveData(Data data, Extracurricular extracurricular) {
        receiveData(data);
    }

    /**
     * Define a instância central de tratamento de informações estruturais na memória.
     * * @param data Hub de dados.
     */
    @Override
    public void setData(Data data) {
        this.data = data;
    }
    
    /**
     * Constrói e renderiza a tela do Catálogo Geral (Catalog.fxml), transmitindo 
     * as coleções de dados unificadas e salvando o histórico da cena de login para navegação reversa.
     * * @param event Evento disparado pelo validador positivo de login.
     */
    void RedirectToCatalog(ActionEvent event) {
        try{
            String fxmlPath = "/fxml/CatalogScreen/Catalog.fxml";
            String pageTitle = "Catalog Screen";
            FXMLLoader fxmlLoader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
            System.out.println("URL carregada: " + fxmlLoader.getLocation());
            Parent root = fxmlLoader.load();

            CatalogController controller = fxmlLoader.getController();

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

            SceneManager.setGoBackScene(screen);

            stage.show();
        }
        catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        
    }
}