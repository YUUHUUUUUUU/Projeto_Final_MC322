package uepa.aplicativo.controllers.CatalogScreen;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.loaders.CatalogCardLoader;

public class CardController {
    
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

    public void setData(String name, String description,
         String initialEnrollmenteDate, String finalEnrollmentDate, Image image) {
            setName(name);
            setDescription(description);
            setInitialEnrollmentDate(initialEnrollmenteDate);
            setFinalEnrollmentDate(finalEnrollmentDate);
            setImage(image);
    }

    private void setName(String name) {
        this.name.setText(name);
    }
    private void setDescription(String description) {
        this.description.setText(description);
    }
    private void setInitialEnrollmentDate(String initialEnrollmentDate) {
        this.initialEnrollmentDate.setText(initialEnrollmentDate);
    }
    private void setFinalEnrollmentDate(String finalEnrollmentDate) {
        this.setFinalEnrollmentDate(finalEnrollmentDate);
    }
    private void setImage(Image image) {
        this.image.setImage(image);
    }

    private List<HBox> cardInitialize() throws Exception{

        /* We create our empty catalog list */
        List<HBox> catalogItems = new ArrayList<>();
        try {
            /* Try loading the EXTRAS data base */
            List<Extracurricular> extracurricularList = new ArrayList<>();

            /* we load the card */
            for(Extracurricular exta : extracurricularList) {

                /* Load a single default card */
                HBox card = CatalogCardLoader.loadCatalogCard();

                /* Fill the card */
            }


        }
        catch(Exception e) {
            throw new Exception("Failed to load cards", e);
        }


    }
}
