package uepa.aplicativo.DataManager;

import java.util.ArrayList;
import java.util.List;

import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.user.Staff;
import uepa.aplicativo.user.Student;
import uepa.aplicativo.user.User;

/**
 * Modelo de gerenciamento centralizado do estado dos dados em memória da aplicação.
 * Armazena as coleções de usuários (Students, Staffs) e atividades extracurriculares,
 * servindo como a ponte operacional de dados para os controladores e os utilitários XML.
 * * @author União de Entidades, Projetos e Atividades
 */
public class Data {

    private List<User> userList = new ArrayList<>();
    private List<Student> studentList = new ArrayList<>();
    private List<Staff> staffList = new ArrayList<>();
    private List<Extracurricular> extracurricularList = new ArrayList<>();
    private User loggedUser;

    /**
     * Inicializa a estrutura de dados agrupando as sublistas de estudantes e staff
     * dentro de uma lista unificada de usuários polimórficos (User).
     * * @param studentList Lista inicial de estudantes lidos da persistência.
     * @param staffList Lista inicial de membros da equipe (Staff) lidos da persistência.
     */
    public Data(List<Student> studentList, List<Staff> staffList) {
        this.studentList = studentList;
        this.staffList = staffList;
        
        for(Student s : studentList) {
            userList.add(s);
        }
        for(Staff s : staffList) {
            userList.add(s);
        }
    }

    /**
     * Define o usuário atualmente autenticado e ativo na sessão do aplicativo desktop.
     * * @param u Instância do usuário logado.
     */
    public void setLoggedUser(User u) {
        this.loggedUser = u;
    }

    /**
     * Atualiza a listagem de estudantes mapeados em memória.
     * * @param studentList Nova lista de estudantes.
     */
    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
    
    /**
     * Retorna a lista contendo todos os estudantes cadastrados.
     * * @return List de Student.
     */
    public List<Student> getStudentList() {
        return studentList;
    }

    /**
     * Atualiza a listagem de membros da Staff mapeados em memória.
     * * @param staffList Nova lista de Staffs.
     */
    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }

    /**
     * Retorna a lista contendo todos os membros da equipe Staff cadastrados.
     * * @return List de Staff.
     */
    public List<Staff> getStaffList() {
        return this.staffList;
    }
    
    /**
     * Retorna a lista unificada de todos os usuários cadastrados no ecossistema (Alunos e Staff).
     * * @return List global de User.
     */
    public List<User> getUserList() {
        return userList;
    }

    /**
     * Retorna a lista contendo todas as atividades extracurriculares registradas.
     * * @return List de Extracurricular.
     */
    public List<Extracurricular> getExtracurricularList() {
        return extracurricularList;
    }

    /**
     * Atualiza a listagem de atividades extracurriculares armazenadas na memória.
     * * @param extracurricularList Nova lista de atividades.
     */
    public void setExtracurricularList(List<Extracurricular> extracurricularList) {
        this.extracurricularList = extracurricularList;
    }

    /**
     * Insere de forma dinâmica um novo usuário em memória, dispara a gravação síncrona 
     * no arquivo XML correspondente e atualiza o estado atualizado dos dados.
     * * @param user Instância do usuário a ser cadastrado.
     * @return Uma nova instância de Data reidratada após a gravação física.
     */
    public Data addUser(User user) {
        if(user != null){
            userList.add(user);
            writeUserData(this);
        }

        return loadData(this);
    }

    /**
     * Insere uma nova atividade extracurricular em memória, dispara a gravação síncrona 
     * no arquivo XML e recarrega os dados para manter o alinhamento.
     * * @param extra Instância da atividade extracurricular.
     * @return Nova instância de Data contendo o estado atualizado.
     */
    public Data addExtracurricular(Extracurricular extra) {
        if(extra != null) {
            extracurricularList.add(extra);
            writeExtracurricularData(this);
        }

        return loadData(this);
    }

    /**
     * Aciona o utilitário xmlWriter para persistir fisicamente as informações dos usuários cadastrados.
     * * @param data Instância de dados contendo os usuários ativos.
     */
    public static void writeUserData(Data data) {
        xmlWriter.writeUsers("/src/main/resources/xml/user.xml", data);
    }

    /**
     * Aciona o utilitário xmlWriter para persistir fisicamente as informações das extracurriculares.
     * * @param data Instância de dados contendo as extracurriculares ativas.
     */
    public static void writeExtracurricularData(Data data) {
        xmlWriter.writeExtracurriculars("/src/main/resources/xml/extra.xml", data);
    }

    /**
     * Realiza a leitura e carregamento completo dos arquivos XML locais para alimentar as listas em memória.
     * * @param data Instância de dados a ser preenchida.
     * @return Instância de Data populada com registros de usuários e atividades.
     */
    public static Data loadData(Data data) {
        data = xmlReader.readUsers("/src/main/resources/xml/user.xml", data);
        data = xmlReader.readExtracurriculars("/src/main/resources/xml/extra.xml", data);

        return data;
    }

    /**
     * Verifica se um determinado usuário já existe na base de dados comparando strings de e-mail.
     * * @param user Entidade contendo o e-mail pesquisado.
     * @param data Instância de dados de checagem.
     * @return true se o e-mail já constar no banco local, false caso contrário.
     * @throws Exception Se a referência de dados fornecida for nula (falha no XML).
     */
    public boolean verifyUserExistence(User user, Data data) throws Exception {
        if(data == null) {
            throw new Exception("Failed to load xml");
        }

        for(User u : data.getUserList()) {
            System.out.println(user.getEmail());
            System.out.println(u.getEmail());
            if(user.getEmail().equals(u.getEmail())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sincroniza e reconstrói as associações de objetos Staff dentro de suas respectivas 
     * atividades extracurriculares vinculando os ponteiros corretos com base em seus IDs.
     */
    public void recreateStaffList() {
        for(Extracurricular e : this.getExtracurricularList()) {
            for(Integer id : e.getStaffsIds()) {
                Staff staff = searchStaff(id);
                e.getStaffList().add(staff);
                System.out.println("staff added");
            }
        }
    }

    /**
     * Realiza uma busca linear na lista de Staffs com base no identificador exclusivo.
     * * @param id Identificador numérico procurado.
     * @return A instância de Staff correspondente ou null caso não seja localizada.
     */
    public Staff searchStaff(Integer id) {
        for(Staff s : this.getStaffList()) {
            if(s.getId() == id) {
                return s;
            }
        }

        return null;
    }
}