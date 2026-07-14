package uepa.aplicativo.extracurricular;

public class StudentAssociation extends Extracurricular{
    private String institute;

    public StudentAssociation(String name, String description){
        super(name, description);
    }

    public String getInstitute(){
        return this.institute;
    }


    

}