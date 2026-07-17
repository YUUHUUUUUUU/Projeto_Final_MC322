package uepa.aplicativo.loaders;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import uepa.aplicativo.controllers.CatalogScreen.CardController;
import uepa.aplicativo.loaders.loadedData.LoadedCard;

/**
 * Classe utilitária especializada na componentização e inflagem dinâmica de layouts FXML.
 * Atua encapsulando o FXMLLoader do JavaFX para gerar de forma síncrona os subcomponentes 
 * gráficos de Cards reutilizáveis dentro das telas do catálogo de projetos e coordenadores.
 * * @author União de Entidades, Projetos e Atividades
 */
public class CatalogCardLoader {
     
    /**
     * Construtor privado para impedir instanciacão da utilidade.
     */
    private CatalogCardLoader() {}

    /**
     * Infla o arquivo FXML base do Card do catálogo principal de extracurriculares.
     * * @return Um wrapper LoadedCard contendo a raiz visual (HBox) e seu respectivo controlador.
     * @throws Exception Se houver falhas físicas ou de leitura sintática do FXML.
     */
    public static LoadedCard loadCatalogCard() throws Exception{

        String fxmlPath = "/fxml/CatalogScreen/Card.fxml";
        return loadCard(fxmlPath);
    }

    /**
     * Infla o layout FXML do Card de perfil focado nos membros da equipe coordenadora (StaffCard).
     * * @return Um wrapper LoadedCard contendo os componentes extraídos do FXML.
     * @throws Exception Se o arquivo FXML for inacessível ou malformado.
     */
    public static LoadedCard loadStaffCard() throws Exception {
        String fxmlPath = "/fxml/CatalogScreen/StaffCard.fxml";
        return loadCard(fxmlPath);
    }

    /**
     * Rotina centralizada de carregamento que inicializa o FXMLLoader, processa o arquivo, 
     * captura o controlador associado e envelopa os resultados dentro de um objeto imutável.
     * * @param fxmlPath Caminho relativo do recurso do SceneBuilder (.fxml).
     * @return Objeto LoadedCard encapsulado.
     * @throws Exception Lança IOException para erros de disco ou ClassCastException em caso de incongruência gráfica.
     */
    public static LoadedCard loadCard(String fxmlPath) throws Exception{

        /* We will need to save the arguments of two parameters
         * the first one is the HBox of the card,
         * and the other one is its Controller (CardController).
         * This will all be saved in LoadedCard, a class created
         * just to save these arguments.
         * * The HBox we will use to add the "card" to the catalog (VBox)
         * and the Controller to edit the information for each "card".
         */
        LoadedCard loadedCard;
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(CatalogCardLoader.class.getResource(fxmlPath)); 
            HBox box = fxmlLoader.load();
            CardController controller = fxmlLoader.getController();
            loadedCard = new LoadedCard(box, controller);
            return loadedCard;
        }
        catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
            throw e;
        }
        catch (ClassCastException e) {
            System.out.println(e);
            e.printStackTrace();
            throw e;
        }
    }
}