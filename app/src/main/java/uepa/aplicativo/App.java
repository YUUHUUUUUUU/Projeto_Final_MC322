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

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
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
        }
    }
}