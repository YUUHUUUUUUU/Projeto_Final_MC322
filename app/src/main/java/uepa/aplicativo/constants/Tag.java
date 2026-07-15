package uepa.aplicativo.constants;
import java.util.List;

import uepa.aplicativo.user.Student;

import java.util.ArrayList;

public enum Tag{
    SPORTS("Esporte",true),
    EXTENSION("Projeto de extensão", true),
    STARTUP("Empresa em formação", true),
    STUDENTCOMPANY("Empresa Jr.",true),
    INTERNSHIP("Estágio", false),
    SPORTCLUB("Atlética",true),
    SCIENTIFICINIT("Iniciação científica",false),
    BOARDGAMES("Jogos de tabuleiro",true);

    public final String nameTag;
    public final boolean GroupActivity;

    Tag(String nameTag, boolean GroupActivity){
        this.nameTag=nameTag;
        this.GroupActivity=GroupActivity;
    }
    
    private List<SubTag> subtags = new ArrayList<SubTag>();

    public List<SubTag> getsubTags(){
        return subtags;
    }
    
    public void addSubTag(SubTag t){
        subtags.add(t);
    }
}
