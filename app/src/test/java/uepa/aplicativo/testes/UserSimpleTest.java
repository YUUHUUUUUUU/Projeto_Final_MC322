package uepa.aplicativo.user;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class UserSimpleTest {

    @Test
    public void createValid() {
        User user = new User("Joao", "joao@uepa.br", "Senha123@", "/foto.png");

        assertEquals("Joao", user.getName());
        assertEquals("joao@uepa.br", user.getEmail());
        assertEquals("Senha123@", user.getPassword());
        assertEquals("/foto.png", user.getPhotoPath());
        assertNotNull(user.getMailBox());
    }

    @Test
    public void reconstructValid() {
        User user = new User("Maria", "maria@uepa.br", "Senha123@", "/foto.png", new ArrayList<>(), "10");

        assertEquals(10, user.getId());
        assertEquals("10", user.getIdString());
        assertEquals("Maria", user.getName());
        assertEquals("maria@uepa.br", user.getEmail());
    }

    @Test
    public void updateNextIdIncrement() {
        User user1 = new User("Ana", "ana@uepa.br", "Senha123@", "/foto.png");
        user1.updateNextId(100);

        User user2 = new User("Pedro", "pedro@uepa.br", "Senha123@", "/foto.png");
        
        assertEquals(101, user2.getId());
    }
}