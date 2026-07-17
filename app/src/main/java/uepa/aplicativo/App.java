package uepa.aplicativo;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import uepa.aplicativo.DataManager.Data;
import uepa.aplicativo.DataManager.xmlReader;
import uepa.aplicativo.DataManager.xmlWriter;
import uepa.aplicativo.SceneManager.SceneManager;
import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.loaders.IconLoader;
import uepa.aplicativo.message.Message;
import uepa.aplicativo.user.Staff;
import uepa.aplicativo.user.Student;
import uepa.aplicativo.user.User;

/**
 * Classe principal de inicialização da aplicação JavaFX (Ponto de Entrada).
 * Responsável pelo fluxo de Bootstrap: carrega o banco de dados local XML, reconstrói
 * associações de objetos e injeta a tela inicial (Login) na janela principal (Stage).
 * * @author União de Entidades, Projetos e Atividades
 */
public class App extends Application {
    
    /**
     * Método principal de execução da JVM. Delega o ciclo de vida para o JavaFX.
     * * @param args Argumentos de linha de comando.
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Ponto de partida do ciclo de vida gráfico do JavaFX.
     * Prepara a engine de dados, carrega os ícones globais e encaminha para o gerenciador de cenas.
     * * @param primaryStage A janela principal (palco) cedida pelo sistema operacional.
     */
    @Override
    public void start(Stage primaryStage) {
        try{
            Data data = null;
            data = Data.loadData(data);
            Data.writeUserData(data);
            Data.writeExtracurricularData(data);

            data.recreateStaffList();

            System.out.println(data.getExtracurricularList().getFirst().getStaffList().getFirst().getEmail());

            IconLoader.tryLoadingIcon(primaryStage);
            String fxmlString = "/fxml/LoginScreen/LoginScreen.fxml";
            //String fxmlString = "/fxml/ExtracurricularScreen/ExtracurricularScreen.fxml";
            String pageTitle = "Login Screen";
            SceneManager.initializeFirstScene(primaryStage, fxmlString, pageTitle, data);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}