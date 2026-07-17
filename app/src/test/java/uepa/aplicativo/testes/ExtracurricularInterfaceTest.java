package uepa.aplicativo.extracurricular;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import uepa.aplicativo.user.Student;
import uepa.aplicativo.user.Staff;

public class ExtracurricularInterfaceTest {

    private Extracurricular extra;
    private Student student;
    private Staff staff;

    @BeforeEach
    public void setUp() {
        extra = new Extracurricular("Nome", "Desc", true, "Inst", "logo", "banner", "link", "fxml");
        student = new Student("Aluno", "aluno@uepa.br", "Senha123@", "photoPath");
        staff = new Staff("Staff", "staff@uepa.br", "Senha123@", "photoPath");
    }

    @Test
    public void addStaffValid() {
        extra.addStaff(staff);
        assertTrue(extra.getStaffList().contains(staff));
        assertTrue(extra.getStaffsIds().contains(staff.getId()));
    }

    @Test
    public void addStaffNull() {
        assertThrows(IllegalArgumentException.class, () -> extra.addStaff(null));
    }

    @Test
    public void addListenerValid() {
        extra.addListener(student);
        assertTrue(extra.getUsersListeners().contains(student));
        assertTrue(extra.getListenersIds().contains(student.getId()));
    }

    @Test
    public void addListenerNull() {
        assertThrows(IllegalArgumentException.class, () -> extra.addListener(null));
    }

    @Test
    public void removeListenerValid() {
        extra.addListener(student);
        extra.removeListener(student);
        assertFalse(extra.getUsersListeners().contains(student));
    }

    @Test
    public void removeListenerNull() {
        assertThrows(IllegalArgumentException.class, () -> extra.removeListener(null));
    }

    @Test
    public void removeListenerNotFound() {
        assertThrows(IllegalArgumentException.class, () -> extra.removeListener(student));
    }

    @Test
    public void notifyListenersCheckMailbox() {
        extra.addListener(student);
        extra.notifyListeners("Aviso", "Texto do aviso");
        
        assertFalse(student.getMailBox().isEmpty());
        assertEquals("Aviso", student.getMailBox().getFirst().getTitle());
        assertEquals("Texto do aviso", student.getMailBox().getFirst().getText());
    }
}