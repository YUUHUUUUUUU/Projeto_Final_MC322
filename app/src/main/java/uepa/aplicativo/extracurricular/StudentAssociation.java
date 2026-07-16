package uepa.aplicativo.extracurricular;

public class StudentAssociation extends Extracurricular{

    public StudentAssociation(String name, String description, boolean openToWork,  String institute,
         String logoPath, String bannerPath,
          String hyperLink){

        String fxmlPath = "/fxml/ExtracurricularScreen/ExtracurricularScreen.fxml";
        super(name, description, openToWork, institute, logoPath, bannerPath, hyperLink, fxmlPath);
    }
}