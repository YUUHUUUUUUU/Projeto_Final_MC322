package uepa.aplicativo.interfaces;

import uepa.aplicativo.message.Message;

/**
 * Interface de Assinatura de Recebimento de Mensagens (Contrato do Ouvinte).
 * * POR QUE ELA EXISTE e COMO FUNCIONA A MAILBOX?
 * Esta interface serve como o contrato do "Observer" (Ouvinte) no sistema de Caixa de Mensagens (Mailbox). 
 * Qualquer classe de usuário que precise receber mensagens no backend (como Student) deve assinar este contrato.
 * Quando implementada, ela expõe o método receiveMessage. No backend da aplicação, quando um alerta oficial 
 * é emitido, a engine localiza a Mailbox interna do usuário (uma lista em memória) e anexa o objeto Message 
 * diretamente a ela, garantindo o recebimento centralizado de dados.
 * * @author União de Entidades, Projetos e Atividades
 */
public interface Notificable {
    
    /**
     * Captura uma mensagem despachada por um transmissor e a deposita dentro da MailBox do usuário.
     * * @param m Objeto contendo o título, texto e remetente da notificação.
     */
    void receiveMessage(Message m);
}