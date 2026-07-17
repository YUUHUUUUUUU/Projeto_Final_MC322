package uepa.aplicativo.SceneManager;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import uepa.aplicativo.DataManager.Data;
import uepa.aplicativo.controllers.LoginScreen.LoginController;
import uepa.aplicativo.loaders.FontLoader;

/**
 * Utilitário centralizador do roteamento e transição de telas do JavaFX.
 * Mantém o histórico de navegação para funcionalidades de "voltar" e manipula a
 * renderização de animações de erro nativas para feedback de interface.
 * * @author União de Entidades, Projetos e Atividades
 */
public class SceneManager {

    private static Scene previousScene;
    private static Scene loginScene;
    
    /* Construtor privado para impedir instanciacão */
    private SceneManager() {}

    /**
     * Representa o método inicializador que constrói a primeiríssima tela gráfica do app.
     * Além de carregar as fontes globais da aplicação (FontLoader), injeta diretamente a 
     * instância master de dados ('Data') no controlador responsável pelo Login.
     * * @param primaryStage A janela base do JavaFX providenciada pelo SO.
     * @param fxmlPath Caminho relativo para o arquivo de layout inicial (.fxml).
     * @param pageTitle O título a ser exibido na barra superior da janela do sistema.
     * @param data O objeto contendo as listas e persistências da aplicação.
     */
    public static void initializeFirstScene(Stage primaryStage, String fxmlPath, String pageTitle, Data data) {
        try {
            FontLoader.loadFonts();
            
            FXMLLoader fxmlLoader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
            Parent root = fxmlLoader.load();
            Scene screen = new Scene(root);
            
            LoginController controller = fxmlLoader.getController();
            controller.receiveData(data);

            root.requestFocus();

            primaryStage.setMaximized(true);
            primaryStage.setTitle(pageTitle);
            primaryStage.setScene(screen);
            primaryStage.setResizable(true);
            primaryStage.show();
        }
        catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        catch (ClassCastException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    /**
     * Dispara uma animação (FadeTransition) que revela suavemente um texto de erro na tela e,
     * após um temporizador estipulado (5 segundos), esmaece o alerta de forma autônoma.
     * * @param errorLabel Componente de texto (Label) vazio embutido previamente no FXML.
     * @param exception A exceção de negócio ou erro capturado pelo bloco Try-Catch da aplicação.
     */
    public static void showErrorMessage(Label errorLabel, Exception exception) {
        String message = exception.getMessage();
        errorLabel.setVisible(true);
        errorLabel.setText(message);
        errorLabel.setOpacity(1);

        FadeTransition fade = new FadeTransition(Duration.millis(5000), errorLabel);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.setOnFinished(e -> {errorLabel.setVisible(false);});
        fade.play();
    }

    /**
     * Armazena estaticamente a referência para a cena anterior antes de uma nova navegação.
     * Permite que botões de "Voltar" não precisem reconstruir o layout do zero.
     * * @param scene A cena gráfica recém-saída.
     */
    public static void setGoBackScene(Scene scene){
        previousScene = scene;
    }

    /**
     * Armazena permanentemente em memória a renderização base do formulário de login
     * para reuso imediato ao encerrar sessões ou realizar navegações longas.
     * * @param scene A cena montada do menu de login.
     */
    public static void setGoBackLogin(Scene scene) {
        loginScene = scene;
    }

    /**
     * Retorna a cena guardada referente à tela inicial de credenciais (Login).
     * @return O objeto Scene do Login.
     */
    public static Scene goBackToLogin() {
        return loginScene;
    }

    /**
     * Recupera a última cena renderizada e registrada no histórico de navegação rotineira.
     * @return O objeto Scene da tela anterior.
     */
    public static Scene getGoBackScene() {
        return previousScene;
    }
}