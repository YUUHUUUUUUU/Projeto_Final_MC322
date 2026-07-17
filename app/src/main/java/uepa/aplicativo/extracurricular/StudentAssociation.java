package uepa.aplicativo.extracurricular;

/**
 * Subclasse de especialização representando Centros Acadêmicos, Diretórios Estudantis ou Ligas Acadêmicas.
 * Mapeia os parâmetros de entrada repassando-os diretamente para a superclasse Extracurricular.
 * * @author União de Entidades, Projetos e Atividades
 */
public class StudentAssociation extends Extracurricular{

    /**
     * Inicializa uma instância de associação estudantil acionando as rotinas básicas de validação.
     * * @param name Nome do Centro Acadêmico ou agremiação representativa.
     * @param description Manifesto de gestão, pautas acadêmicas e objetivos coletivos.
     * @param openToWork Status de editais de participação abertos para chapas ou colaboradores.
     * @param institute Curso ou Instituto ao qual o órgão estudantil representa.
     * @param logoPath Caminho da identidade visual/brasão da associação.
     * @param bannerPath Caminho do banner visual do órgão.
     * @param hyperLink URL para canais de ouvidoria estudantil externos.
     * @param fxmlPath Arquivo de layout visual JavaFX.
     */
    public StudentAssociation(String name, String description, boolean openToWork,  String institute,
         String logoPath, String bannerPath,
          String hyperLink, String fxmlPath){
        super(name, description, openToWork, institute, logoPath, bannerPath, hyperLink, fxmlPath);
    }
}