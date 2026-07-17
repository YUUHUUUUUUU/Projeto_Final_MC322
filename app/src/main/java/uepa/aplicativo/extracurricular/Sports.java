package uepa.aplicativo.extracurricular;

/**
 * Subclasse de especialização que caracteriza uma atividade extracurricular esportiva (Atléticas ou Treinos).
 * Mapeia os parâmetros de entrada repassando-os diretamente para a superclasse Extracurricular.
 * * @author União de Entidades, Projetos e Atividades
 */
public class Sports extends Extracurricular {
    
        /**
         * Inicializa uma instância esportiva acionando as rotinas básicas de validação.
         * * @param name Nome da modalidade esportiva ou equipe universitária.
         * @param description Cronograma de treinos, campeonatos e requisitos físicos.
         * @param openToWork Status de seletivas e treinos abertos para novos atletas.
         * @param institute Campus ou centro organizador da atlética.
         * @param logoPath Caminho do escudo ou logotipo do esporte.
         * @param bannerPath Caminho do banner promocional esportivo.
         * @param hyperLink URL para formulário de inscrição física.
         * @param fxmlPath Arquivo de layout visual JavaFX.
         */
        public Sports(String name, String description, boolean openToWork,  String institute,
         String logoPath, String bannerPath,
          String hyperLink, String fxmlPath){
        super(name, description, openToWork, institute, logoPath, bannerPath, hyperLink, fxmlPath);
    }
}