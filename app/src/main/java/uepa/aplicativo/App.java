package uepa.aplicativo;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import uepa.aplicativo.DataManager.Data;
import uepa.aplicativo.DataManager.xmlReader;
import uepa.aplicativo.DataManager.xmlWriter;
import uepa.aplicativo.SceneManager.SceneManager;
import uepa.aplicativo.loaders.IconLoader;
import uepa.aplicativo.message.Message;
import uepa.aplicativo.user.User;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        /* Teste de escrita e leitura */
        Data data = new Data();
        List<Message> mailBox = new ArrayList<>();
        Message m = new Message("tituloMensagem", "uma mensagem pequena", "criador");
        mailBox.add(m);
        User user = new User("nome", "email", "password", "/logo/UEPA.png", mailBox);
        data.addUser(user);
        xmlWriter.writeUsers("/src/main/resources/xml/saida.xml", data);
        List<User> listUser = xmlReader.readUsers("/src/main/resources/xml/saida.xml");
        System.out.println("A lista é: ");
        if(listUser == null) {
            System.out.println("lista é nula");
        }
        for(User u : listUser) {
            System.out.println(u.getName());
            System.out.println(u.getEmail());
            System.out.println(u.getPassword());
            System.out.println(u.getPhotoPath());

            System.out.println("Mailbox:");
            List<Message> uMB = u.getMailBox();
            Message msg = uMB.getFirst();
            System.out.println("titulo: " + msg.getTitle());
            System.out.println("text: " + msg.getText());
            System.out.println("creator: " + msg.getCreatorName());
        }

        IconLoader.tryLoadingIcon(primaryStage);
        //String fxmlString = "/fxml/CatalogScreen/Catalog.fxml";
        String fxmlString = "/fxml/ExtracurricularScreen/ExtracurricularScreen.fxml";
        String pageTitle = "Login Screen";
        SceneManager.initializeFirstScene(primaryStage, fxmlString, pageTitle);
    }
}