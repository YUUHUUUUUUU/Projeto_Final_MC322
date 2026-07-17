package uepa.aplicativo.DataManager;

import java.io.FileOutputStream;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.message.Message;
import uepa.aplicativo.user.User;

/**
 * Utilitário estruturado encarregado da serialização de dados (mecanismo Writer).
 * Converte objetos complexos em memória em blocos estruturados de texto XML através da 
 * API StAX (XMLStreamWriter), mantendo os arquivos atualizados em tempo real.
 * * @author União de Entidades, Projetos e Atividades
 */
public class xmlWriter {
    
    /**
     * Inicializa a escrita abrindo o arquivo físico e configurando o cabeçalho do documento XML.
     * * @param xmlPath Localização relativa do arquivo de gravação.
     * @return Instância ativa de XMLStreamWriter.
     * @throws Exception Em caso de falha de I/O ou permissões de gravação de arquivos.
     */
    private static XMLStreamWriter startWriter(String xmlPath) throws Exception{
        String absolutePath = System.getProperty("user.dir") + xmlPath;
        System.out.println(absolutePath);
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        FileOutputStream file = new FileOutputStream(absolutePath);
        XMLStreamWriter writer = factory.createXMLStreamWriter(file, "UTF-8");

        writer.writeStartDocument("UTF-8", "1.0");

        return writer;
    }

    /**
     * Efetua o flush para descarregar o buffer de dados e encerra o fluxo do arquivo físico.
     * * @param writer Instância ativa de gravação.
     * @throws Exception Se houver problemas na finalização do arquivo.
     */
    private static void closeWriter(XMLStreamWriter writer) throws Exception{
        writer.flush();
        writer.close();

    }

    /**
     * Converte e grava sequencialmente todos os usuários mapeados em nós do arquivo XML.
     * Serializa de forma aninhada o histórico completo de mensagens contido em suas Mailboxes.
     * * @param xmlPath Caminho relativo do arquivo XML de saída.
     * @param data Central de dados contendo os objetos ativos em memória.
     */
    public static void writeUsers(String xmlPath, Data data) {
        try{
            XMLStreamWriter writer = startWriter(xmlPath);
            List<User> userList = data.getUserList();

            writer.writeStartElement("userlist");
            for(User u : userList) {

                writer.writeStartElement("user");

                /* attributes of user */
                writeElement(writer, "name", u.getName());
                writeElement(writer, "email", u.getEmail());
                writeElement(writer, "password", u.getPassword());
                writeElement(writer, "photoPath", u.getPhotoPath());
                writeElement(writer, "role", u.getRole().toString());
                writeElement(writer, "id", u.getIdString());

                writer.writeStartElement("mailbox");
                for(Message m : u.getMailBox()) {

                    /* for every message we write a new element "message" */
                    writer.writeStartElement("message");

                    writeElement(writer, "title", m.getTitle());
                    writeElement(writer, "text", m.getText());
                    writeElement(writer, "creatorname", m.getCreatorName());

                    writer.writeEndElement();
                }

                /* close "mailbox" and "user" elements */
                writer.writeEndElement();
                writer.writeEndElement();
            }

            /* close the "userlist" element */
            writer.writeEndElement();

            closeWriter(writer);
        }
        catch (Exception e) {
            System.out.println("Failed to write XML File for User");
            e.printStackTrace();
        }

    }

    /**
     * Converte e grava a coleção de atividades extracurriculares no arquivo extra.xml.
     * Serializa sublistas de inteiros representando as chaves relacionais de Staff e Ouvintes.
     * * @param xmlPath Caminho de gravação do arquivo XML.
     * @param data Central de dados em memória.
     */
    public static void writeExtracurriculars(String xmlPath, Data data) {
        try{
            XMLStreamWriter writer = startWriter(xmlPath);
            List<Extracurricular> extracurricularList = data.getExtracurricularList();

            writer.writeStartElement("extracurricularlist");
            for(Extracurricular e : extracurricularList) {

                writer.writeStartElement("extra");

                /* attributes of extra */
                writeElement(writer, "name", e.getName());
                writeElement(writer, "description", e.getDescription());
                writeElement(writer, "institute", e.getInstitute());
                writeElement(writer, "isopenstring", e.getIsOpenString());
                writeElement(writer, "fxmlpath", e.getFxmlPath());
                writeElement(writer, "hyperlink", e.getHyperLink());
                writeElement(writer, "bannerpath", e.getBannerPath());
                writeElement(writer, "logopath", e.getLogoPath());
                writeElement(writer, "id", e.getIdString());

                writer.writeStartElement("staffids");
                for(Integer i : e.getStaffsIds()) {

                    /* for every staffId we write a new element "message" */
                    writeElement(writer, "idstaff", Integer.toString(i));
                }
                writer.writeEndElement(); // close staffsids

                writer.writeStartElement("listenersids");
                for(Integer i : e.getListenersIds()) {
                    writeElement(writer, "idlistener", Integer.toString(i));
                }
                writer.writeEndElement(); // close listernersIds
            
                writer.writeEndElement(); // close the Staff
            }

            /* close the "extracurricularlist" element */
            writer.writeEndElement();

            closeWriter(writer);
        }
        catch (Exception e) {
            System.out.println("Failed to write XML File for User");
            e.printStackTrace();
        }
    }

    /**
     * Cria uma abertura e fechamento de tag XML simplificada injetando o conteúdo interno correspondente.
     * Trata de forma segura entradas nulas injetando um corpo de texto vazio.
     * * @param writer Instância do gerador ativo.
     * @param tag Nome do elemento XML.
     * @param value Conteúdo textual a ser inserido.
     * @throws Exception Se houver problemas no fluxo de caracteres.
     */
    private static void writeElement(XMLStreamWriter writer, String tag, String value) throws Exception{

        writer.writeStartElement(tag);

        if(value == null){
            writer.writeCharacters("");
        }
        else {
            writer.writeCharacters(value);
        }

        writer.writeEndElement();

    }
}