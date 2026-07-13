package uepa.aplicativo.loaders;

import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.stage.Stage;

public class IconLoader {
    
    private IconLoader() {}

    public static void tryLoadingIcon(Stage primaryStage) {

        String logoPath = "/logo/UEPA.png";
        InputStream iconStream = IconLoader.class.getResourceAsStream(logoPath);
        
        if(iconStream == null) {
            System.out.println("Icon not found at path: " + logoPath);
        }

        try {
            Image appIcon = new Image(iconStream);
            primaryStage.getIcons().add(appIcon);
            System.out.println("Icon loaded!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
