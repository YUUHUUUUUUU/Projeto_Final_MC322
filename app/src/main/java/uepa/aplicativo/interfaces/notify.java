package uepa.aplicativo.interfaces;

/**
 * Interface de Disparo e Propagação de Mensagens em Lote (Contrato do Transmissor).
 * * POR QUE ELA EXISTE e COMO FUNCIONA A MAILBOX?
 * Esta interface atua como o contrato do "Subject/Publisher" (Transmissor) no ecossistema da Caixa de Mensagens.
 * Classes que gerenciam ou disparam comunicados (como Staff ou a própria Extracurricular) implementam notify.
 * O funcionamento consiste em percorrer a coleção de ouvintes (uma lista de Users que assinam Notificable) 
 * que demonstraram interesse no projeto e invocar o método de entrega individual de cada um. Isso faz com 
 * que a mensagem se propague instantaneamente para as caixas postais de dezenas de estudantes no backend em um clique.
 * * @author União de Entidades, Projetos e Atividades
 */
public interface notify {
    
    /**
     * Executa a iteração em massa sobre as contas registradas como ouvintes, instanciando
     * e distribuindo uma notificação idêntica para todas as Mailboxes associadas.
     * * @param title O título ou assunto do aviso oficial.
     * @param text O texto explicativo detalhado emitido pela coordenação.
     */
    void notifyListeners(String title, String text);
}