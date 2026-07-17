package uepa.aplicativo.interfaces;

import uepa.aplicativo.DataManager.Data;
import uepa.aplicativo.extracurricular.Extracurricular;

/**
 * Interface de Roteamento e Compartilhamento de Escopo do JavaFX.
 * * POR QUE ELA EXISTE?
 * Quando trocamos de tela (Scene) no JavaFX através de cliques em botões, o framework destrói 
 * ou limpa o controlador antigo e constrói um novo. Para não perdermos a sessão do usuário logado 
 * e as listas carregadas, os Controllers das telas implementam esta interface. O SceneManager usa 
 * as assinaturas abaixo para injetar a instância unificada de dados ('Data') e o contexto de uma 
 * 'Extracurricular' selecionada diretamente no novo controlador que assumirá a janela gráfica.
 * * @author União de Entidades, Projetos e Atividades
 */
public interface RecieveData {
    
    /**
     * Transmite a base de dados global para inicialização estrita do estado da nova tela.
     * * @param data Instância unificada de armazenamento em memória.
     */
    public void receiveData(Data data);
    
    /**
     * Transmite a base de dados em conjunto com uma atividade extracurricular selecionada, 
     * permitindo que a tela carregue e renderize informações dinâmicas específicas de um card clicado.
     * * @param data Instância unificada de armazenamento em memória.
     * @param extracurricular Atividade extracurricular que está sendo focada ou editada.
     */
    public void receiveData(Data data, Extracurricular extracurricular);
    
    /**
     * Define internamente o atributo de referência para o manipulador de dados do escopo da tela.
     * * @param data Instância de dados a ser injetada.
     */
    public void setData(Data data);
}