package uepa.aplicativo.controllers.CatalogScreen;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import uepa.aplicativo.DataManager.Data;
import uepa.aplicativo.SceneManager.SceneManager;
import uepa.aplicativo.controllers.ExtracurricularScreen.ExtracurricularController;
import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.interfaces.RecieveData;
import uepa.aplicativo.user.Staff;

/**
 * Controlador granular encarregado de injetar o estado dos dados lógicos em componentes 
 * visuais customizados reutilizáveis de cartões gráficos (Cards FXML).
 * Atua de forma polimórfica mapeando layouts para dados de Atividades ou perfis de Staff.
 * * @author União de Entidades, Projetos e Atividades
 */
public class CardController implements RecieveData {
    
    Data data;

    @FXML
    private HBox card;

    @FXML
    private ImageView image;

    @FXML
    private Label name;

    @FXML
    private Label description;

    @FXML
    private Label initialEnrollmentDate;

    @FXML
    private Label finalEnrollmentDate;

    @FXML
    private Label type;

    @FXML
    private Label institute;

    @FXML
    private Button seeMoreButton;

    /**
     * Captura o evento de clique do botão "Saber Mais" do card e aciona o roteador gráfico.
     * * @param event Evento gerado pelo clique do botão.
     */
    @FXML
    void clickedSeeMore(ActionEvent event) {
        RedirectToExtra(event);
    }

    private Extracurricular extracurricular;
    private Staff staff;

    /**
     * Extrai os atributos internos da atividade extracurricular injetada e altera de 
     * forma dinâmica as propriedades dos rótulos de texto e visualizadores de imagem JavaFX.
     */
    private void applyData() {
            String name = extracurricular.getName();
            String description = extracurricular.getDescription();
            Image image = extracurricular.getLogo();
            setName(name);
            setDescription(description);
            setImage(image);
            
            this.type.setText(extracurricular.getInstitute());
            System.out.println(extracurricular.getOpenToWork());
            if(extracurricular.getOpenToWork()) {
                this.initialEnrollmentDate.setText("Aberta");
            }
            else {
                this.initialEnrollmentDate.setText("Fechada");
            }
    }

    /**
     * Modifica o texto do rótulo do nome do Card.
     * * @param name Nome textual.
     */
    private void setName(String name) {
        this.name.setText(name);
    }
    
    /**
     * Modifica o texto do rótulo da descrição/e-mail do Card.
     * * @param description Texto da ementa.
     */
    private void setDescription(String description) {
        this.description.setText(description);
    }
    
    /**
     * Modifica o frame gráfico do ImageView embutido.
     * * @param image Objeto de imagem carregado do asset.
     */
    private void setImage(Image image) {
        this.image.setImage(image);
    }


    /**
     * Define a instância estrutural da atividade atrelada a este elemento visual do catálogo.
     * * @param extra Objeto da extracurricular focado.
     */
    private void setExtracurricular(Extracurricular extra) {
        extracurricular = extra;
    }

    /**
     * Exibe um log informando o sucesso da injeção de dados no componente gráfico.
     */
    public void printExtra(){
        System.out.println("Extracurricular successfully loaded: " + extracurricular.getName());
    }

    /**
     * Ponto centralizado de ancoragem de dados de extracurriculares. Encapsula as rotinas 
     * internas aplicando o modelo de dados e imprimindo logs de controle em lote.
     * * @param extra Entidade extracurricular a ser convertida em Card visual.
     */
    public void loadExtracurricular(Extracurricular extra) {
        setExtracurricular(extra);
        applyData();
        printExtra();
    }

    /**
     * Acopla o perfil de um membro da coordenação de Staff para fins de reuso polimórfico 
     * do componente gráfico para renderização de perfis de pessoas.
     * * @param s Entidade Staff.
     */
    public void loadStaff(Staff s) {
        setStaff(s);
        applyStaffData();
    }

    /**
     * Define internamente o atributo contendo a entidade Staff associada.
     * * @param staff Entidade Staff.
     */
    private void setStaff(Staff staff) {
        this.staff = staff;
    }

    /**
     * Extrai e mapeia as informações de credenciais de Staff e e-mail institucional 
     * aplicando-as nos rótulos padrão reaproveitados do Card FXML.
     */
    private void applyStaffData(){
        String name = staff.getName();
        String description = staff.getEmail();
        Image image = staff.getPhoto();
        setName(name);
        setDescription(description);
        setImage(image);
    }

    /**
     * Sincroniza e herda a referência unificada do manipulador Data.
     * * @param data Central de dados.
     */
    @Override
    public void receiveData(Data data) {
        setData(data);
    }

    /**
     * Sobrecarga polimórfica de sincronização de dados contextuais da interface.
     * * @param data Central de dados.
     * @param extra Instância da extracurricular.
     */
    @Override
    public void receiveData(Data data, Extracurricular extra){
        receiveData(data);
    }

    /**
     * Define a propriedade interna contendo a instância de dados do escopo em memória.
     * * @param data Central de dados.
     */
    @Override
    public void setData(Data data) {
        this.data = data;
    }

    /**
     * Constrói de forma dinâmica o contêiner visual de detalhes da Extracurricular (ExtracurricularScreen.fxml),
     * captura o controlador criado e injeta o contexto da atividade clicada antes de projetá-la no palco principal.
     * * @param event Evento originado pelo disparo do botão de expansão do card.
     */
    void RedirectToExtra(ActionEvent event) {
        try{
            String fxmlPath = "/fxml/ExtracurricularScreen/ExtracurricularScreen.fxml";
            String pageTitle = extracurricular.getName() + " Screen";
            FXMLLoader fxmlLoader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
            System.out.println("URL carregada: " + fxmlLoader.getLocation());
            Parent root = fxmlLoader.load();

            ExtracurricularController controller = fxmlLoader.getController();

            controller.receiveData(data, extracurricular);

            Scene screen = new Scene(root);

            Node source = (Node) event.getSource();
            Scene currentScene = source.getScene();
            Stage stage = (Stage) currentScene.getWindow();

            root.requestFocus();
            stage.setTitle(pageTitle);
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