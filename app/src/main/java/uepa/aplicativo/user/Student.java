package uepa.aplicativo.user;
import java.util.List;

import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.message.Message;
import uepa.aplicativo.constants.Role;

/**
 * Especialização de User voltada para a representação de alunos e participantes gerais.
 * Define o nível de permissão (Role) nativo como STUDENT.
 * * @author União de Entidades, Projetos e Atividades
 */
public class Student extends User{
    
    /**
     * CONSTRUTOR 1: Instanciação Inicial (Novos Cadastros).
     * Acionado quando o fluxo de cadastro cria um aluno inédito na aplicação.
     * Repassa os parâmetros para inicialização de ID incremental na superclasse.
     * * @param name Nome completo do estudante.
     * @param email E-mail de cadastro.
     * @param password Senha da conta.
     * @param photoPath Caminho do asset de imagem da foto.
     */
    public Student(String name, String email, String password, String photoPath){
        super(name, email, password, photoPath);
        role = Role.STUDENT;
    }

    /**
     * CONSTRUTOR 2: Reconstrução por carregamento de dados (XML).
     * Acionado exclusivamente na inicialização do sistema pelo DataManager.
     * Mapeia e injeta as chaves primárias fixas já pertencentes a este registro em disco,
     * bem como reidrata a coleção histórica de notificações na MailBox.
     * * @param name Nome carregado do XML.
     * @param email E-mail carregado do XML.
     * @param password Senha carregada do XML.
     * @param photoPath Caminho da foto carregado do XML.
     * @param mailBox Lista de mensagens arquivadas recuperada do XML.
     * @param idString ID imutável serializado do banco local.
     */
    public Student(String name, String email, String password, String photoPath, List<Message> mailBox, String idString){
        super(name, email, password, photoPath, mailBox, idString);
        role = Role.STUDENT;
    }

    /**
     * Adiciona uma atividade extracurricular à listagem de favoritos do estudante.
     * (Nota de Desenvolvimento: Implementação pendente de ajuste de lógica estrutural).
     * * @param e Atividade extracurricular selecionada.
     */
    public void favorite(Extracurricular e){
        //favorites.add(e); aqui vai ficar com problema mesmo, extracurricular so pode ser 1 dos dois
    }

    /**
     * Método interno de interface para processamento de rotinas de notificação.
     * * @param e Atividade extracurricular alvo.
     */
    public void notify(Extracurricular e){
        
    }

    //public void addExtra(Extracurricular e){
    //    following.add(e);
    //}

    /**
     * Encerra a sessão autenticada do usuário na aplicação desktop.
     */
    public void logout(){

    }
}