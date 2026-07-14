package uepa.aplicativo.loaders.loadedData;

import javafx.scene.layout.HBox;
import uepa.aplicativo.controllers.CatalogScreen.CardController;

public class LoadedCard {
    private final HBox root;
    private final CardController controller;

    public LoadedCard(HBox root, CardController controller) {
        this.root = root;
        this.controller = controller;
    }

    public CardController getController() {
        return controller;
    }

    public HBox getRoot() {
        return root;
    }
}
