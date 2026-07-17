package uepa.aplicativo.controllers.CatalogScreen;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import uepa.aplicativo.loaders.CatalogCardLoader;
import uepa.aplicativo.loaders.loadedData.LoadedCard;
import uepa.aplicativo.DataManager.Data;
import uepa.aplicativo.SceneManager.SceneManager;
import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.interfaces.RecieveData;

/**
 * Controlador central do painel principal da aplicação (Catalog Screen).
 * Responsável por gerenciar o container vertical do catálogo, acionar de forma iterativa 
 * os infladores de cartões dinâmicos e preencher a interface com os projetos disponíveis.
 * * @author União de Entidades, Projetos e Atividades
 */
public class CatalogController implements RecieveData {

    @FXML
    private VBox catalog;

    @FXML
    private Button mailBoxButton;

    /**
     * Intercepta o clique do botão da Mailbox.
     * (Nota de Desenvolvimento: Componente visual de exibição de lista pendente nesta versão).
     * * @param event Ação gerada pelo clique do botão de caixa postal.
     */
    @FXML
    void mailBoxClicked(ActionEvent event) {

    }

    public Data data;

    /**
     * Acessa a coleção de extracurriculares disponível em memória, itera sobre cada item 
     * disparando o inflador assíncrono CatalogCardLoader, injeta o escopo no CardController correspondente 
     * e anexa as estruturas HBox prontas em uma lista sequencial utilizável de componentes estruturados.
     * * @return List contendo as instâncias gráficas estruturadas nó-raiz de HBox de cada cartão gerado.
     * @throws Exception Se a base de extracurriculares estiver vazia ou se houver erros de I/O estruturais em arquivos FXML.
     */
    private List<HBox> loadCatalog() throws Exception{

        /* We create our empty catalog list */
        List<HBox> catalogItems = new ArrayList<>();
        try {

            /* We get the extras list from database */
            List<Extracurricular> extracurricularList = data.getExtracurricularList();

            /* we load the card */
            if(!extracurricularList.isEmpty()){
                for(Extracurricular extra : extracurricularList) {

                    /* Load a single default card */
                    LoadedCard loadedCard = CatalogCardLoader.loadCatalogCard();

                    /* Get the card controller */
                    CardController controller = loadedCard.getController();
                    HBox card = loadedCard.getRoot();

                    if(controller == null) {
                        throw new Exception("Controller can not be null");
                    }
                    if (card == null) {
                        throw new Exception("Card can not be null");
                    }
                    /* Load the extracurricular into the card */
                    controller.loadExtracurricular(extra);

                    /* add the card to the list */
                    catalogItems.add(card);
                }
            }
        }
        catch(Exception e) {
            throw new Exception("Failed to load cards", e);
        }

        if(catalogItems.isEmpty()){
            throw new Exception("Catalog is empty");
        }

        return catalogItems;

    }

    /**
     * Recebe a central de dados, aciona a rotina interna de montagem assíncrona do catálogo, 
     * efetua a limpeza completa de resíduos gráficos do contêiner VBox e injeta sequencialmente 
     * cada um dos cartões (Cards) gerados para exibição em tela na interface com o usuário.
     * * @param data Hub centralizado de dados instanciado em memória.
     */
    @Override
    public void receiveData(Data data) {
        setData(data);
        try {
            /* We load the list of cards */
            List<HBox> catalogItems = loadCatalog();

            /* We clear and add each item to the catalog */
            catalog.getChildren().clear();

            for(HBox card : catalogItems) {
                catalog.getChildren().add(card);
            }
            System.out.println("Hello Catalog!");
        }
        catch(Exception e) {
            System.out.println("Failed to load the Catalog");
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }

    /**
     * Método polimórfico de sincronização que repassa de forma interna a carga de dados global.
     * * @param data Hub de dados.
     * @param extracurricular Contexto específico secundário.
     */
    @Override
    public void receiveData(Data data, Extracurricular extracurricular) {
        receiveData(data);
    }

    /**
     * Define a propriedade que armazena a instância central do hub unificado de dados em memória.
     * * @param data Hub unificado de dados.
     */
    @Override
    public void setData(Data data) {
        this.data = data;
    }
}