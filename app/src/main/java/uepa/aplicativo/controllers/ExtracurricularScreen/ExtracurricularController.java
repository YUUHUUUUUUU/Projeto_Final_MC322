package uepa.aplicativo.controllers.ExtracurricularScreen;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import uepa.aplicativo.controllers.CatalogScreen.CardController;
import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.extracurricular.StudentAssociation;
import uepa.aplicativo.loaders.CatalogCardLoader;
import uepa.aplicativo.loaders.loadedData.LoadedCard;
import uepa.aplicativo.user.Staff;

public class ExtracurricularController implements Initializable,Editable {

    @FXML
    private Button goBackButton;

    @FXML
    private Hyperlink hyperlink;

    @FXML
    private VBox staffCatalog;

    @FXML
    void goBack(ActionEvent event) {

    }

    @FXML
    void openLink(ActionEvent event) {
        System.out.println("heyyy");
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        try {
            /* We load the extra */
            // extracurricular =
            List<HBox> catalogItems = loadCatalog();
            
            if(catalogItems == null) {
                System.out.println("Staff catalog is null");
            }

            System.out.println("Heyyyy");
            /* We clear and add each item to the catalog */
            staffCatalog.getChildren().clear();

            for(HBox card : catalogItems) {
                staffCatalog.getChildren().add(card);
            }
            System.out.println("Hello Staff Catalog!");
        }
        catch(Exception e) {
            System.out.println("Failed to load the Catalog");
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
        
    }

    Extracurricular extracurricular;


    private List<HBox> loadCatalog() throws Exception{

        /* We create our empty catalog list */
        List<HBox> catalogItems = new ArrayList<>();
        try {
            /* Try loading the EXTRAS data base */
            // we will probably use a static method for this!
            // maybe on initialize

            // extracurricular = 

            /* JUST FOR TESTING: we will be adding manually some extras */
            Extracurricular extra1 = new StudentAssociation("Extra1", "Desc1", true, "IFGW", "/logo/UEPA.png", "/logo/UEPA.png", "googlextracurricular.com");
            Staff professorTeste = new Staff("email", "name", extra1, "/logo/UEPA.png");
            extra1.addStaff(professorTeste);
            Staff professorTeste2 = new Staff("email2", "nome2", extra1, "/logo/UEPA.png");
            extra1.addStaff(professorTeste2);

            this.extracurricular = extra1;
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

    @Override
    public void editExtraName(String newName){
        extracurricular.setName(newName);
    }

    @Override
    public void editExtraDesc(String newDesc){
        extracurricular.setDescription(newDesc);
    }

    @Override
    public void editExtraOpen(boolean newOpen){
        extracurricular.setOpenToWork(newOpen);
    }

    @Override
    public void editExtraLink(String newHyper){
        extracurricular.setHyperLink(newHyper);
    }

    @Override
    public void editExtraInstitute(String newInst){
        extracurricular.setInstitute(newInst);
    }

    @Override
    public void editExtraBanner(String newPath){
        extracurricular.setBannerPath(newPath);
    }

    @Override
    public void editExtraLogo(String newPath){
        extracurricular.setLogoPath(newPath);
    }
}

