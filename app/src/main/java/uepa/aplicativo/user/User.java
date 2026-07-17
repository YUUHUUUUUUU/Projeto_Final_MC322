package uepa.aplicativo.user;
import java.util.ArrayList;
import java.util.List;

import uepa.aplicativo.constants.Role;
import uepa.aplicativo.interfaces.*;
import uepa.aplicativo.message.*;

import javafx.scene.image.Image;
import uepa.aplicativo.loaders.ImageLoader;

/**
 * Classe base abstrata/estrutural que representa um usuário dentro do sistema UEPA.
 * Concentra os atributos comuns de identificação, regras cadastrais de segurança e
 * implementa o contrato Notificable para recebimento de comunicações.
 * * @author União de Entidades, Projetos e Atividades
 */
public class User implements Notificable{

    Role role;
    private String name;
    private String email;
    private String password;

    private Image photo;
    private String photoPath;

    private List<Message> mailBox;

    private static int nextId = 0;
    private final int id;
    
    /**
     * CONSTRUTOR 1: Criação de Novos Registros.
     * Deve ser acionado unicamente quando um novo usuário está se cadastrando no sistema.
     * Como ele ainda não possui um registro persistido em disco, este construtor se encarrega
     * de autoincrementar e assinar uma ID inédita usando o contador estático em memória.
     * * @param name O nome completo informado no cadastro.
     * @param email O endereço eletrônico informado no cadastro.
     * @param password A senha padrão escolhida pelo usuário.
     * @param photoPath O caminho do recurso de imagem para foto de perfil.
     */
    public User(String name, String email, String password, String photoPath){

        this.id = nextId++; // gera id novo


        this.email = email;
        this.password = password;
        this.name = name;
        this.photoPath = photoPath;
        setPhoto(photoPath);
        this.mailBox = new ArrayList<>();
    }

    /**
     * CONSTRUTOR 2: Reconstrução via Persistência (XML).
     * Deve ser acionado unicamente pelo DataManager (xmlReader) ao iniciar a aplicação.
     * Ao carregar os dados salvos em disco, o sistema precisa reinjetar o ID original
     * exato do usuário para não quebrar os vínculos referenciados por outros objetos. 
     * Ele converte o ID de String para inteiro e reajusta o teto do nextId para mitigar riscos de colisão.
     * * @param name O nome do usuário recuperado do arquivo XML.
     * @param email O e-mail do usuário recuperado do arquivo XML.
     * @param password A senha correspondente recuperada do arquivo XML.
     * @param photoPath O caminho da imagem recuperado do arquivo XML.
     * @param mailBox O histórico completo da caixa de mensagens do usuário.
     * @param idString O identificador numérico único serializado como texto no XML.
     */
    public User(String name, String email,
         String password, String photoPath,
          List<Message> mailBox, String idString) {
        
        /* converts the idString back to int */
        this.id = Integer.parseInt(idString);
        updateNextId(id);

        this.email = email;
        this.name = name;
        this.password = password;
        this.photoPath = photoPath;
        this.mailBox = mailBox;
        setPhoto(photoPath);
    }

    /**
     * Ajusta dinamicamente o contador estático de IDs globais se o ID recém-carregado
     * do XML for maior ou igual ao ponteiro atual. Previne duplicidade de chaves primárias.
     * * @param existingId Código identificador recuperado da persistência local.
     */
    public void updateNextId(int existingId) {
        if(existingId >= nextId) {
            nextId = existingId + 1;
        }
    }

    /**
     * Converte o identificador numérico em formato String.
     * Facilita a escrita de tags de atributos no gerador xmlWriter.
     * * @return Representação textual do ID.
     */
    public String getIdString() {
        return Integer.toString(id);
    }

    /**
     * Retorna o identificador numérico imutável assinado a este usuário.
     * * @return O ID nativo (int).
     */
    public int getId() {
        return this.id;
    }

    /**
     * Retorna a permissão/papel estrutural do perfil dentro do sistema.
     * * @return Enum correspondente (STUDENT ou STAFF).
     */
    public Role getRole() {
        return role;
    }

    /**
     * Instancia e carrega um fluxo de imagem gráfica do JavaFX correspondente ao photoPath.
     * * @return Objeto Image carregado a partir do ResourceAsStream.
     */
    public Image getPhoto() {
        return new Image(getClass().getResourceAsStream(this.photoPath));
    }

    /**
     * Retorna a String contendo o caminho relativo do arquivo de imagem em disco.
     * * @return O caminho da foto de perfil.
     */
    public String getPhotoPath() {
        return photoPath;
    }

    /**
     * Modifica o endereço do caminho relativo da foto de perfil.
     * * @param photoPath Nova String de caminho para a imagem.
     */
    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    /**
     * Executa a tentativa de leitura física da imagem através da classe utilitária ImageLoader.
     * Consome a exceção de forma segura em caso de falha de carregamento no path.
     * * @param photoPath Caminho relativo da imagem a ser lida pelo sistema.
     */
    public void setPhoto(String photoPath) {
        try {
            photo = ImageLoader.LoadImage(photoPath);
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Retorna o nome registrado para o usuário.
     * * @return O nome do usuário.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Define o nome do usuário aplicando regras de validação estritas (Fail-Fast).
     * O nome não pode ser nulo, vazio, composto apenas por espaços e não pode exceder 50 caracteres.
     * * @param name Proposta de String para o nome do usuário.
     * @throws IllegalArgumentException Se o nome violar critérios de tamanho ou presença de dados.
     */
    public void setName(String name){
        // Check for null or empty strings
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }

        // Check length constraints
        if(name.length() > 50){
            throw new IllegalArgumentException("Name cannot exceed 50 characters.");
        }

        this.name = name;
    }

    /**
     * Retorna a credencial de senha atual do usuário.
     * * @return A senha em texto plano.
     */
    public String getPassword(){
        return this.password;
    }
    
    /**
     * Retorna o e-mail cadastrado para o usuário.
     * * @return O e-mail do usuário.
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * Define o e-mail limpando espaços adicionais nas extremidades através do trim().
     * Bloqueia e rejeita entradas nulas ou vazias.
     * * @param email Proposta de String para e-mail do usuário.
     * @throws IllegalArgumentException Se a entrada for nula ou sem caracteres válidos.
     */
    public void setEmail(String email){
       if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        this.email = email.trim();
    }

    /**
     * Atualiza a senha da conta aplicando checagens complexas de segurança por expressões regulares.
     * Exige comprimento mínimo de 8 dígitos, presença de ao menos uma letra maiúscula, uma minúscula,
     * um símbolo/caractere especial e proíbe terminantemente o uso de espaços vazios.
     * * @param newPassword Nova string de senha proposta.
     * @throws IllegalArgumentException Se a senha descumprir as exigências de segurança de criptografia.
     */
    public void changePassword(String newPassword){
        // Null check
        if(password == null || password.trim().isEmpty()){
            throw new IllegalArgumentException("Password cannot be empty.");
        }
        // Length check
        if(password.length() < 8){
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }
        // Uppercase letter check
        if(!password.matches(".*[A-Z].*")){
            throw new IllegalArgumentException("Password must contain at least one uppercase letter.");
        }
        // Lowercase letter check
        if(!password.matches(".*[a-z].*")){
            throw new IllegalArgumentException("Password must contain at least one lowercase letter.");
        }
        // Special character check
        if(!password.matches(".*[^a-zA-Z0-9].*")){
            throw new IllegalArgumentException("Password must contain at least one special symbol.");
        }
        // No spaces check
        if(password.contains(" ")){
            throw new IllegalArgumentException("Password cannot contain spaces.");
        }
        // Assign the password
        this.password = newPassword; 
    }

    /**
     * Retorna a lista contendo todas as mensagens depositadas na caixa de correio do usuário.
     * * @return A lista (List) de objetos do tipo Message.
     */
    public List<Message> getMailBox(){
        return mailBox;
    }

    /**
     * Insere de forma segura um novo objeto de mensagem na MailBox do usuário.
     * Método acionado via disparo de eventos do padrão de projeto Observer.
     * * @param m Instância de Message enviada pela atividade extracurricular.
     */
    @Override
    public void receiveMessage(Message m) {
        if (m == null) {
            return;
        }
        mailBox.add(m);
    }
}