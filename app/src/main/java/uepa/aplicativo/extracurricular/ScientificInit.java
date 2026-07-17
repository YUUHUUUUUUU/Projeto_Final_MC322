package uepa.aplicativo.extracurricular;

/**
 * Subclasse de especialização que caracteriza uma atividade de Iniciação Científica (Pesquisa Acadêmica).
 * Mapeia os parâmetros de entrada repassando-os diretamente para a superclasse Extracurricular.
 * * @author União de Entidades, Projetos e Atividades
 */
public class ScientificInit extends Extracurricular {
    
        /**
         * Inicializa uma instância de Iniciação Científica acionando as rotinas básicas de validação.
         * * @param name Nome do projeto ou linha de pesquisa científica.
         * @param description Resumo do plano de trabalho e escopo acadêmico.
         * @param openToWork Status de seleção de bolsistas/voluntários em aberto.
         * @param institute Laboratório ou Instituto organizador.
         * @param logoPath Caminho do logotipo da pesquisa.
         * @param bannerPath Caminho do banner descritivo.
         * @param hyperLink URL para acesso a editais oficiais.
         * @param fxmlPath Arquivo de layout visual JavaFX.
         */
        public ScientificInit(String name, String description, boolean openToWork,  String institute,
         String logoPath, String bannerPath,
          String hyperLink, String fxmlPath){
        super(name, description, openToWork, institute, logoPath, bannerPath, hyperLink, fxmlPath);
    }
}