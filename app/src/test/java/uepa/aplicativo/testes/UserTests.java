package uepa.aplicativo.testes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import uepa.aplicativo.user.User;
import uepa.aplicativo.message.Message;
import uepa.aplicativo.interfaces.notifiable;

public class UserTests {

    private StudentTest user1;
    private StudentTest user2;
    private List<Message> mensagensEsperadas;

    private class StudentTest extends User implements notifiable {
        private List<Message> inbox = new ArrayList<>();

        public StudentTest(String email, String name, String photoPath) {
            super(email, name, photoPath);
        }

        @Override
        public void receiveMessage(Message message) {
            this.inbox.add(message);
        }

        public List<Message> getInbox() {
            return inbox;
        }
    }

    @BeforeEach
    public void setUp() {
        user1 = new StudentTest("aluno1@uepa.br", "Carlos Silva", "/fotos/carlos.png");
        user2 = new StudentTest("aluno2@uepa.br", "Ana Souza", "/fotos/ana.png");
        mensagensEsperadas = new ArrayList<>();
    }

    @Test
    public void testAdicionarMensagensENotificacoes() {
        Message msg1 = new Message("Bem-vindo", "Bem-vindo ao sistema da UEPA");
        Message msg2 = new Message("Nova Atividade", "Inscrições abertas para o projeto");

        user1.receiveMessage(msg1);
        user1.receiveMessage(msg2);

        assertEquals(2, user1.getInbox().size());
        assertEquals("Bem-vindo", user1.getInbox().get(0).getTitle());
        assertEquals("Inscrições abertas para o projeto", user1.getInbox().get(1).getText());
    }

    @Test
    public void testCompararListasDeMensagens() {
        Message msg1 = new Message("Aviso", "Reunião amanhã às 14h");
        Message msg2 = new Message("Urgente", "Entrega do relatório final");

        mensagensEsperadas.add(msg1);
        mensagensEsperadas.add(msg2);

        user1.receiveMessage(msg1);
        user1.receiveMessage(msg2);

        assertEquals(mensagensEsperadas, user1.getInbox());
    }

    @Test
    public void testCompararListasDeUsuariosNotificados() {
        List<notifiable> listaNotificados = new ArrayList<>();
        List<notifiable> listaEsperada = new ArrayList<>();

        listaNotificados.add(user1);
        listaNotificados.add(user2);

        listaEsperada.add(user1);
        listaEsperada.add(user2);

        assertEquals(listaEsperada.size(), listaNotificados.size());
        assertIterableEquals(listaEsperada, listaNotificados);
    }

    @Test
    public void testIsolamentoDeMensagensEntreUsuarios() {
        Message msg = new Message("Exclusivo", "Mensagem apenas para o Carlos");
        
        user1.receiveMessage(msg);

        assertFalse(user1.getInbox().isEmpty());
        assertTrue(user2.getInbox().isEmpty());
        assertNotEquals(user1.getInbox(), user2.getInbox());
    }
}