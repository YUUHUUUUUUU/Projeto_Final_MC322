package uepa.aplicativo.extracurricular;

/**
 * Subclasse de especialização que caracteriza uma atividade extracurricular do tipo Estágio.
 * Mapeia os parâmetros de entrada repassando-os diretamente para a superclasse Extracurricular.
 * * @author União de Entidades, Projetos e Atividades
 */
public class Internship extends Extracurricular {
    
        /**
         * Inicializa uma instância de Estágio acionando as rotinas básicas de validação.
         * * @param name Nome da vaga ou instituição concedente de estágio.
         * @param description Requisitos e ementa do estágio.
         * @param openToWork Status de vagas em aberto.
         * @param institute Instituto coordenador.
         * @param logoPath Caminho do logotipo do estágio.
         * @param bannerPath Caminho do banner visual do estágio.
         * @param hyperLink URL para submissão de currículos ou editais.
         * @param fxmlPath Arquivo de layout visual JavaFX.
         */
        public Internship(String name, String description, boolean openToWork,  String institute,
         String logoPath, String bannerPath,
          String hyperLink, String fxmlPath){
        super(name, description, openToWork, institute, logoPath, bannerPath, hyperLink, fxmlPath);
    }
}