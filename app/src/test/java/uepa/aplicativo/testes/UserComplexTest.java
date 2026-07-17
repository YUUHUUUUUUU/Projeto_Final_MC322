package uepa.aplicativo.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import uepa.aplicativo.message.Message;

public class UserComplexTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("Joao", "joao@uepa.br", "Senha123@", "/foto.png");
    }

    @Test
    public void setNameNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> user.setName(""));
        assertThrows(IllegalArgumentException.class, () -> user.setName("   "));
        assertThrows(IllegalArgumentException.class, () -> user.setName(null));
    }

    @Test
    public void setNameTooLong() {
        String longName = "a".repeat(51);
        assertThrows(IllegalArgumentException.class, () -> user.setName(longName));
    }

    @Test
    public void setEmailNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> user.setEmail(""));
        assertThrows(IllegalArgumentException.class, () -> user.setEmail("   "));
        assertThrows(IllegalArgumentException.class, () -> user.setEmail(null));
    }

    @Test
    public void changePasswordValid() {
        user.changePassword("NovaSenha1!");
        assertEquals("NovaSenha1!", user.getPassword());
    }

    @Test
    public void changePasswordInvalid() {
        assertThrows(IllegalArgumentException.class, () -> user.changePassword(""));
        assertThrows(IllegalArgumentException.class, () -> user.changePassword(null));
        assertThrows(IllegalArgumentException.class, () -> user.changePassword("curta1!"));
        assertThrows(IllegalArgumentException.class, () -> user.changePassword("semmaiuscula1!"));
        assertThrows(IllegalArgumentException.class, () -> user.changePassword("SEMminuscula1!"));
        assertThrows(IllegalArgumentException.class, () -> user.changePassword("SemSimbolo123"));
        assertThrows(IllegalArgumentException.class, () -> user.changePassword("Com Espaco1!"));
    }

    @Test
    public void receiveMessageValid() {
        Message msg = new Message("Titulo", "Texto", "Admin");
        user.receiveMessage(msg);

        assertEquals(1, user.getMailBox().size());
        assertEquals("Titulo", user.getMailBox().getFirst().getTitle());
        assertEquals("Texto", user.getMailBox().getFirst().getText());
    }

    @Test
    public void receiveMessageNull() {
        user.receiveMessage(null);
        assertTrue(user.getMailBox().isEmpty());
    }
}