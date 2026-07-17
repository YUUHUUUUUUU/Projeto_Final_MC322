package uepa.aplicativo.user;
import java.util.List;

import uepa.aplicativo.constants.Role;
import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.interfaces.notify;
import uepa.aplicativo.message.Message;

/**
 * Especialização de User que representa os coordenadores, professores e administradores do sistema.
 * Implementa a interface notify (padrão Observer) permitindo disparar comunicados diretos em lote
 * para todos os alunos associados como ouvintes (Listeners) em sua respectiva atividade extracurricular.
 * * @author União de Entidades, Projetos e Atividades
 */
public class Staff extends User implements notify{
    private Extracurricular e;

    /**
     * CONSTRUTOR 1: Instanciação Inicial (Novos Cadastros).
     * Acionado quando o fluxo administrativo cria uma conta inédita de Staff na aplicação.
     * Garante a atribuição automática de um ID incremental e configura a Role para STAFF.
     * * @param name Nome do coordenador/membro da equipe.
     * @param email E-mail institucional de login.
     * @param password Senha de acesso.
     * @param photoPath Caminho relativo da imagem de perfil.
     */
    public Staff(String name, String email, String password, String photoPath){
        super(name, email, password, photoPath);
        role = Role.STAFF;
    }

    /**
     * CONSTRUTOR 2: Reconstrução por carregamento de dados (XML).
     * Acionado exclusivamente no carregamento inicial do banco de dados StAX local.
     * Garante o reaproveitamento das chaves referenciais gravadas no arquivo local, evitando
     * colisões de IDs e restaurando o estado das mensagens salvas no XML.
     * * @param name Nome do Staff carregado do XML.
     * @param email E-mail institucional carregado do XML.
     * @param password Senha resgatada do XML.
     * @param photoPath Caminho do recurso gráfico resgatado do XML.
     * @param mailBox Caixa de mensagens arquivadas resgatada do XML.
     * @param idString ID serializado em formato texto.
     */
    public Staff(String name, String email, String password, String photoPath, List<Message> mailBox, String idString){
        super(name, email, password, photoPath, mailBox, idString);
        role = Role.STAFF;
    }

    /**
     * Dispara e envia um comunicado oficial em massa. O método itera sobre a lista de ouvintes
     * da atividade extracurricular vinculada, instanciando objetos Message e populando as
     * Mailboxes de todos os alunos registrados.
     * * @param title O cabeçalho ou título informativo do aviso.
     * @param text O corpo do texto descritivo contendo as orientações do aviso.
     */
    @Override
    public void notifyListeners(String title, String text){
        Message m = new Message(title, text, getName());
        for (int i = 0; i < e.getUsersListeners().size(); i++) {
            e.getUsersListeners().get(i).receiveMessage(m);
        }
    }
}