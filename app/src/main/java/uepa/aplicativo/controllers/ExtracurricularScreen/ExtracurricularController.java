package uepa.aplicativo.controllers.ExtracurricularScreen;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import uepa.aplicativo.DataManager.Data;
import uepa.aplicativo.SceneManager.SceneManager;
import uepa.aplicativo.controllers.CatalogScreen.CardController;
import uepa.aplicativo.controllers.CatalogScreen.CatalogController;
import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.interfaces.RecieveData;
import uepa.aplicativo.loaders.CatalogCardLoader;
import uepa.aplicativo.loaders.loadedData.LoadedCard;
import uepa.aplicativo.message.Message;
import uepa.aplicativo.user.Staff;

/**
 * Controlador JavaFX encarregado do gerenciamento da tela focada de detalhes de uma Extracurricular.
 * Renderiza dinamicamente dados específicos de banners, ementas, links externos e reconstrói 
 * a lista de coordenadores associados injetando-os em um sub-catálogo visual de cards verticais.
 * * @author União de Entidades, Projetos e Atividades
 */
public class ExtracurricularController implements RecieveData{

    @FXML
    private ImageView banner;

    @FXML
    private Label description;

    @FXML
    private Button goBackButton;

    @FXML
    private Label name;

    @FXML
    private Hyperlink hyperlink;

    @FXML
    private Label type;

    @FXML
    private VBox staffCatalog;

    /**
     * Retorna a interface do usuário de volta para a visualização ampla do Catálogo Geral.
     * * @param event Ação gerada pelo clique do botão voltar.
     */
    @FXML
    void goBack(ActionEvent event) {
        RedirectToCatalog(event);
    }

    /**
     * Dispara um comando nativo do sistema operacional Linux (xdg-open) para abrir a URL 
     * de inscrição externa configurada para este projeto no navegador padrão do usuário.
     * * @param event Ação disparada pelo acionamento do elemento Hyperlink na interface.
     */
    @FXML
    void openLink(ActionEvent event) {
        try {
            Runtime.getRuntime().exec(new String[]{
                "xdg-open",
                extracurricular.getHyperLink()
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    Extracurricular extracurricular;
    Data data;


    /**
     * Itera sobre a lista de coordenadores (Staff) pertencentes a esta atividade específica,
     * aciona o inflador CatalogCardLoader para gerar instâncias de StaffCard de forma dinâmica, 
     * atualiza os textos de perfil dos membros e agrupa os elementos em uma lista visual de nós HBox.
     * * @return List contendo as instâncias estruturadas de HBox dos cards inflados.
     * @throws Exception Se a Staff estiver vazia ou se houver problemas de renderização nos subcomponentes FXML.
     */
    private List<HBox> loadCatalog() throws Exception{

        /* We create our empty catalog list */
        List<HBox> catalogItems = new ArrayList<>();
        try {
            /* we load the card */
            if(!extracurricular.getStaffList().isEmpty()){
                for(Staff s : extracurricular.getStaffList()) {
                    /* Load a single default card */
                    LoadedCard loadedCard = CatalogCardLoader.loadStaffCard();

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
                    controller.loadStaff(s);

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
     * Sincroniza o contexto do hub Data e captura o objeto de Extracurricular mapeado pelo card.
     * Efetua a reidratação imediata de todos os nós visuais (título, ementa, logo e imagens) 
     * e limpa o container VBox injetando as caixas de contêineres horizontais da Staff correspondente.
     * * @param data Base de dados.
     * @param extracurricular Instância específica da atividade clicada.
     */
    @Override
    public void receiveData(Data data, Extracurricular extracurricular)  {
        setData(data);
        this.extracurricular = extracurricular;
        this.description.setText(this.extracurricular.getDescription());
        this.name.setText(this.extracurricular.getName());
        this.hyperlink.setText(this.extracurricular.getHyperLink());
        this.type.setText(this.extracurricular.getInstitute());
        this.banner.setImage(this.extracurricular.getBanner());
        try {
            List<HBox> catalogItems = loadCatalog();
            
            if(catalogItems == null) {
                System.out.println("Staff catalog is null");
            }

            /* We clear and add each item to the catalog */
            staffCatalog.getChildren().clear();

            for(HBox card : catalogItems) {
                staffCatalog.getChildren().add(card);
            }
        }
        catch(Exception e) {
            System.out.println("Failed to load the Catalog");
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }

    /**
     * Assinatura polimórfica de contrato vazia por exigir o escopo complementar da extracurricular.
     * * @param data Base unificada de dados.
     */
    @Override
    public void receiveData(Data data) {}

    /**
     * Injeta a referência unificada do manipulador de coleções.
     * * @param data Base de dados em memória.
     */
    @Override
    public void setData(Data data) {
        this.data = data;
    }

    /**
     * Restaura a cena salva anteriormente no histórico do SceneManager para efetuar 
     * o retorno limpo de tela rumo ao Catálogo Geral sem reinicializações destrutivas de dados.
     * * @param event Ação contendo o clique do botão de retorno.
     */
    void RedirectToCatalog(ActionEvent event) {
        try{
            Scene screen = SceneManager.getGoBackScene();

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

}