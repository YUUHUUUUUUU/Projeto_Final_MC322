package uepa.aplicativo.loaders.loadedData;

import javafx.scene.layout.HBox;
import uepa.aplicativo.controllers.CatalogScreen.CardController;

/**
 * Objeto de transferência e envelopamento de dados gráficos inflados (padrão Wrapper).
 * Encapsula de forma indissociável a raiz visual do contêiner e o respectivo controlador do card FXML.
 * * @author União de Entidades, Projetos e Atividades
 */
public class LoadedCard {
    private final HBox root;
    private final CardController controller;

    /**
     * Constrói o invólucro agrupando as instâncias gráficas criadas pelo FXMLLoader.
     * * @param root Contêiner horizontal (HBox) nó-raiz da interface do Card.
     * @param controller Instância ligada do CardController.
     */
    public LoadedCard(HBox root, CardController controller) {
        this.root = root;
        this.controller = controller;
    }

    /**
     * Retorna o controlador lógico associado a este componente de Card inflado.
     * * @return CardController vinculável.
     */
    public CardController getController() {
        return controller;
    }

    /**
     * Retorna a raiz estrutural do layout visual para inserção direta em containers VBox.
     * * @return HBox nó-raiz gráfico.
     */
    public HBox getRoot() {
        return root;
    }
}