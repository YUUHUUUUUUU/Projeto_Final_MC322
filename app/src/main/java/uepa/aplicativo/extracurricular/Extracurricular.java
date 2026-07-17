package uepa.aplicativo.extracurricular;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import uepa.aplicativo.user.Staff;
import uepa.aplicativo.user.User;
import uepa.aplicativo.interfaces.notify;
import uepa.aplicativo.message.Message;

/**
 * Entidade base de domínio representando uma Atividade Extracurricular na UEPA.
 * Implementa a interface notify atuando como o "Subject" (Sujeito) no padrão Observer:
 * mantém registros de usuários interessados (Listeners) e despacha notificações em lote 
 * para alimentar o mecanismo de Mailbox no backend sempre que novidades são postadas.
 * * @author União de Entidades, Projetos e Atividades
 */
public class Extracurricular implements notify{
    
    private String name;
    private String description;
    private boolean openToWork;
    private String institute;
    private String isOpenString;
    private String fxmlPath;
    private String hyperlink;

    private List<User> listeners = new ArrayList<>();
    private List<Staff> staffList = new ArrayList<>();


    private List<Integer> listenersId = new ArrayList<>();
    private List<Integer> staffsId = new ArrayList<>();

    private String bannerPath;
    private String logoPath;


    private static int nextId = 0;
    private final int id;

    /**
     * CONSTRUTOR 1: Criação de Novas Atividades.
     * Deve ser utilizado exclusivamente no fluxo de inserção de uma nova extracurricular em memória.
     * Como o objeto ainda não existe no XML, este construtor gera automaticamente um identificador único
     * e sequencial com base no contador interno estático nextId.
     *
     * @param name Nome da atividade.
     * @param description Descrição detalhada de objetivos.
     * @param openToWork Estado inicial indicando se aceita novas inscrições.
     * @param institute Campus ou Instituto ao qual está vinculada.
     * @param logoPath Caminho relativo para a imagem do logotipo.
     * @param bannerPath Caminho relativo para a imagem do banner visual.
     * @param hyperlink Link externo para inscrições ou formulários externos.
     * @param fxmlPath Caminho do layout visual da tela específica do card.
     */
    public Extracurricular(String name, String description, boolean openToWork,  String institute,
         String logoPath, String bannerPath,
          String hyperlink, String fxmlPath) {
        
        id = nextId++;

        setName(name);
        setDescription(description);
        setOpenToWork(openToWork);
        setInstitute(institute);
        setHyperLink(hyperlink);
        setBannerPath(bannerPath);
        setLogoPath(logoPath);

        setFxmlPath(fxmlPath);

    }

    /**
     * CONSTRUTOR 2: Reconstrução via Persistência (XML).
     * Deve ser invocado unicamente pelo xmlReader durante o bootstrap do ecossistema de dados.
     * Restaura a chave primária imutável salva no XML para garantir a integridade dos vínculos de IDs 
     * e das listas de Staffs e Alunos, além de sincronizar o ponteiro estático nextId para evitar colisões.
     * * @param name Nome restaurado do XML.
     * @param description Descrição restaurada do XML.
     * @param isOpenString Texto binário ("true"/"false") do estado de abertura.
     * @param institute Instituto organizador recuperado.
     * @param logoPath Caminho da imagem de logo.
     * @param bannerPath Caminho da imagem de banner.
     * @param hyperlink URL externa associada.
     * @param fxmlPath Caminho de exibição JavaFX.
     * @param staffsIds Coleção textual de IDs pertencentes à coordenação.
     * @param listenersIds Coleção textual de IDs pertencentes aos alunos ouvintes.
     * @param idString Identificador numérico nativo gravado originalmente em disco.
     */
    public Extracurricular(String name, String description, String isOpenString,  String institute,
         String logoPath, String bannerPath,
          String hyperlink, String fxmlPath, 
          List<String> staffsIds, List<String> listenersIds, String idString) {

        /* converts the idString back to int */
        this.id = Integer.parseInt(idString);
        updateNextId(id);

        
        setName(name);
        setDescription(description);
        setOpenToWork(isOpenString);
        setInstitute(institute);
        setHyperLink(hyperlink);
        setBannerPath(bannerPath);
        setLogoPath(logoPath);
        setFxmlPath(fxmlPath);

        setListenerList(listenersIds);
        setStaffList(staffsIds);
    }

    /**
     * Atualiza o teto máximo seguro para o autoincremento evitando colisões de ID 
     * ao instanciar novos objetos no ciclo de vida em memória.
     * * @param existingId ID recém-carregado do XML.
     */
    public void updateNextId(int existingId) {
        if(existingId >= nextId) {
            nextId = existingId + 1;
        }
    }

    /**
     * Popula a lista temporária de IDs da Staff a partir de dados em String carregados do XML.
     * * @param staffIds Lista de chaves identificadoras textuais.
     */
    public void setStaffList(List<String> staffIds) {
        for(String s : staffIds) {
            this.staffsId.add(Integer.parseInt(s));
        }
    }

    /**
     * Popula a lista temporária de IDs de Ouvintes a partir de dados em String carregados do XML.
     * * @param listenerIds Lista de chaves identificadoras textuais.
     */
    public void setListenerList(List<String> listenerIds){
        for(String s : listenerIds) {
            this.listenersId.add(Integer.parseInt(s));
        }
    }

    /**
     * Retorna o estado de abertura mapeado em String para gravação direta via xmlWriter.
     * * @return "true" ou "false".
     */
    public String getIsOpenString(){
        return this.isOpenString;
    }

    /**
     * Retorna o ID estrutural convertido em String para serialização local em arquivos XML.
     * * @return ID textual.
     */
    public String getIdString() {
        return Integer.toString(id);
    }

    /**
     * Retorna o ID numérico nativo primário desta atividade.
     * * @return O ID numérico (int).
     */
    public int getId() {
        return this.id;
    }

    /**
     * Retorna o nome da atividade extracurricular.
     * * @return O nome.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Define o nome descritivo da atividade.
     * * @param n Proposta de nome para a extracurricular.
     * @return true confirmando a atribuição.
     */
    public boolean setName(String n){
        this.name=n;
        return true;
    }

    /**
     * Retorna a ementa ou descrição longa informativa da atividade.
     * * @return Texto descritivo.
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Retorna o estado booleano de disponibilidade de vagas/processo seletivo da atividade.
     * * @return true se estiver ativa para ingresso, false se fechada.
     */
    public boolean getOpenToWork(){
        return openToWork;
    }

    /**
     * Sobrecarga de setter utilitária para mapear strings binárias provenientes do XML 
     * para o atributo de controle lógico booleano interno.
     * * @param isOpenString String contendo "true" ou "false".
     */
    public void setOpenToWork(String isOpenString){
        this.isOpenString = isOpenString;
        if(isOpenString.equals("true")){
            this.openToWork=true;
        }
        else if(isOpenString.equals("false")){
            this.openToWork=false;
        }
    }

    /**
     * Retorna o instituto acadêmico responsável pela promoção deste projeto.
     * * @return Nome do instituto.
     */
    public String getInstitute(){
        return institute;
    }

    /**
     * Define a descrição longa aplicando regras de tamanho limite de segurança.
     * * @param description Texto informativo proposto.
     * @throws IllegalArgumentException Se for nula, em branco ou possuir mais de 300 caracteres.
     */
    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição não pode estar vazia.");
        }
        if (description.length() > 300) {
            throw new IllegalArgumentException("Descrição muito longa (máximo de 1000 caracteres).");
        }
        this.description = description;
    }

    /**
     * Modifica o status lógico de disponibilidade de vagas e atualiza de forma síncrona 
     * a string de serialização do banco XML.
     * * @param openToWork Booleano de estado.
     */
    public void setOpenToWork(boolean openToWork) {
        this.openToWork = openToWork;
        if(this.openToWork) {
            this.isOpenString = "true";
        }
        else {
            this.isOpenString = "false";
        }
    }

    /**
     * Define o Instituto responsável validando critérios de nulidade.
     * * @param institute Nome do campus ou faculdade de origem.
     * @throws IllegalArgumentException Se a entrada fornecida for nula ou em branco.
     */
    public void setInstitute(String institute) {
        if (institute == null || institute.trim().isEmpty()) {
            throw new IllegalArgumentException("O instituto não pode ser nulo ou vazio.");
        }
        this.institute = institute;
    }

    /**
     * Define o caminho relativo para localização do banner em disco de recursos.
     * * @param bannerPath String contendo o subdiretório do asset.
     * @throws IllegalArgumentException Se a string de localização for vazia ou nula.
     */
    public void setBannerPath(String bannerPath) {
        if (bannerPath == null || bannerPath.trim().isEmpty()) {
            throw new IllegalArgumentException("O caminho do banner não pode ser nulo ou vazio.");
        }
        this.bannerPath = bannerPath;
    }


    /**
     * Retorna a string do caminho do recurso do banner.
     * * @return Caminho relativo do banner.
     */
    public String getBannerPath() {
        return bannerPath;
    }

    /**
     * Retorna a localização do layout FXML associado à tela desta extracurricular.
     * * @return Caminho do arquivo FXML.
     */
    public String getFxmlPath() {
        return fxmlPath;
    }

    /**
     * Define a localização do layout FXML para renderização dinâmica no SceneManager.
     * * @param fxmlPath Caminho relativo do arquivo FXML.
     */
    public void setFxmlPath(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

    /**
     * Retorna a lista contendo as instâncias de objetos Staff vinculadas à coordenação deste projeto.
     * * @return Lista (List) de Staffs.
     */
    public List<Staff> getStaffList() {
        return this.staffList;
    }

    /**
     * Associa um novo coordenador à equipe administrativa da atividade extracurricular, 
     * registrando tanto o objeto real quanto o espelhamento de ID numérico dele para o banco de dados.
     * * @param staff Instância da conta de Staff.
     * @throws IllegalArgumentException Se a conta de Staff informada for nula.
     */
    public void addStaff(Staff staff) {
        if (staff == null) {
            throw new IllegalArgumentException("O membro da equipe não pode ser nulo.");
        }
        else {
            int id = staff.getId();
            this.staffList.add(staff);
            this.staffsId.add(id);
        }
    }

    /**
     * Retorna a lista de usuários (geralmente estudantes) inscritos para receber notificações desta atividade.
     * * @return Lista de objetos User ouvintes.
     */
    public List<User> getUsersListeners(){
        return listeners;
    }

    /**
     * Retorna a lista contendo estritamente os IDs inteiros de todos os usuários ouvintes cadastrados.
     * * @return Lista de inteiros representando IDs dos ouvintes.
     */
    public List<Integer> getListenersIds() {
        return listenersId;
    }

    /**
     * Retorna a lista contendo estritamente os IDs inteiros de todos os membros da coordenação cadastrados.
     * * @return Lista de inteiros representando IDs de Staff.
     */
    public List<Integer> getStaffsIds() {
        return staffsId;
    }

    /**
     * Insere diretamente um usuário à lista interna de ouvintes de disparos de mensagens.
     * * @param s Instância de User a ser acoplada.
     */
    public void addtoNotify(User s){
        listeners.add(s);
    }

    /**
     * Despacha e distribui um comunicado em lote para as mailboxes no backend. 
     * Instancia um objeto Message injetando o título, conteúdo e assinando com o nome desta 
     * extracurricular, iterando e entregando para cada ouvinte registrado na atividade.
     * * @param title O título descritivo do comunicado.
     * @param text O conteúdo da mensagem enviado pela coordenação.
     */
    @Override
    public void notifyListeners(String title, String text) {

        Message m = new Message(title, text, getName());
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).receiveMessage(m);
        }
    }

    /**
     * Insere um novo usuário na lista de ouvintes validando nulidades e adicionando seu ID 
     * de forma síncrona para espelhamento e gravação de XML posterior.
     * * @param listener Objeto do usuário interessado em receber avisos.
     * @throws IllegalArgumentException Se o ouvinte fornecido for nulo.
     */
    public void addListener(User listener) {
        if (listener == null) {
            throw new IllegalArgumentException("O ouvinte de notificações não pode ser nulo.");
        }
        else {
            int id = listener.getId();
            this.listeners.add(listener);
            this.listenersId.add(id);
        }
    }

    /**
     * Remove um usuário da lista de assinaturas de notificações da atividade extracurricular.
     * * @param listener O objeto de usuário a ser removido da lista.
     * @throws IllegalArgumentException Se for nulo ou se o usuário não for encontrado na lista.
     */
    public void removeListener(User listener) {
        if (listener == null) {
            throw new IllegalArgumentException("O ouvinte de notificações não pode ser nulo.");
        }
        if (!this.listeners.contains(listener)) {
            throw new IllegalArgumentException("O ouvinte não foi encontrado na lista de notificações.");
        }
        this.listeners.remove(listener);
    }

    /**
     * Modifica o endereço do recurso FXML visual associado à tela de exibição.
     * * @param fxml String contendo a localização do arquivo FXML.
     */
    public void setfxmlPath(String fxml){
        this.fxmlPath=fxml;
    }

    /**
     * Retorna o status de abertura atual do projeto.
     * * @return true se aceita membros, false caso contrário.
     */
    public boolean isOpenToWork() {
        return openToWork;
    }

    /**
     * Retorna a String contendo a localização do asset gráfico do logo da atividade.
     * * @return Caminho relativo do logo.
     */
    public String getLogoPath() {
        return logoPath;
    }

    /**
     * Retorna a string do hiperlink externo cadastrado.
     * * @return Link da atividade.
     */
    public String getHyperLink() {
        return this.hyperlink;
    }

    /**
     * Modifica de forma segura a string do caminho relativo do logotipo.
     * * @param logoPath String contendo a localização do asset do logotipo.
     */
    public void setLogoPath(String logoPath) {
        if(logoPath != null) {
            this.logoPath = logoPath;
        }
    }

    /**
     * Instancia em tempo de execução um elemento gráfico Image do JavaFX a partir do logoPath.
     * * @return Objeto Image carregado do Stream de recursos.
     */
    public Image getLogo() {
        return new Image(getClass().getResourceAsStream(this.logoPath));
    }

    /**
     * Instancia em tempo de execução um elemento gráfico Image do JavaFX a partir do bannerPath.
     * * @return Objeto Image carregado do Stream de recursos.
     */
    public Image getBanner() {
        return new Image(getClass().getResourceAsStream(this.bannerPath));
    }

    /**
     * Modifica a URL do hiperlink para formulários ou portais externos de inscrição.
     * * @param link String contendo a URL externa da atividade.
     */
    public void setHyperLink(String link) {
        if(link != null) {
            this.hyperlink = link;
        }
    }
}