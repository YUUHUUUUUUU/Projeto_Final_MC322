package uepa.aplicativo.loaders;

import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Utilitário focado no carregamento e atribuição do ícone de identificação da barra de título.
 * Lê o brasão institucional e o anexa de forma direta à pilha de ícones do contêiner Stage do JavaFX.
 * * @author União de Entidades, Projetos e Atividades
 */
public class IconLoader {
    
    /**
     * Construtor privado para evitar instanciacão.
     */
    private IconLoader() {}

    /**
     * Captura a imagem em formato stream do logotipo da UEPA e tenta embuti-la como ícone do Stage principal.
     * Trata possíveis erros de caminho ausente exibindo logs no console.
     * * @param primaryStage O palco (Stage) principal em exibição no monitor.
     */
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