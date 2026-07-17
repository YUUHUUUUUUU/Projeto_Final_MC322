package uepa.aplicativo.constants;

/
public enum Tag {
    SPORTS("Esporte"),
    EXTENSION("Extensão"),
    STARTUP("Start-up"),
    STUDENTCOMPANY("Empresa Júnior"),
    INTERNSHIP("Estágio"),      
    SPORTCLUB("Clube de Esportes"),  
    SCIENTIFICINIT("Iniciação Científica"), 
    BOARDGAMES("Jogos de Tabuleiro"); 
    
    private final String tagName;

    Tag(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return this.tagName;
    }
}