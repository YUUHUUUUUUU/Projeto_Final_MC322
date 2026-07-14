package uepa.aplicativo.loaders.loadedData;

import javafx.scene.layout.HBox;
import uepa.aplicativo.controllers.CatalogScreen.CardController;

public class LoadedCard {
    public final HBox root;
    public final CardController controller;

    public LoadedCard(HBox root, CardController controller) {
        this.root = root;
        this.controller = controller;
    }
}
