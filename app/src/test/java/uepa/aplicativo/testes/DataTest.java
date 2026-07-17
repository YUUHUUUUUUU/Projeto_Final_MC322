package uepa.aplicativo.DataManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import uepa.aplicativo.user.Student;
import uepa.aplicativo.user.Staff;
import java.util.ArrayList;
import java.util.List;

public class DataTest {

    private Data data;
    private Student student;
    private Staff staff;

    @BeforeEach
    public void setUp() {
        student = new Student("Aluno", "aluno@uepa.br", "Senha123@", "foto.png");
        staff = new Staff("Professor", "prof@uepa.br", "Senha123@", "foto.png");

        List<Student> students = new ArrayList<>();
        students.add(student);

        List<Staff> staffs = new ArrayList<>();
        staffs.add(staff);

        data = new Data(students, staffs);
    }

    @Test
    public void verifyUserExistenceTrue() throws Exception {
        Student tempUser = new Student("Teste", "aluno@uepa.br", "123", "foto");
        assertTrue(data.verifyUserExistence(tempUser, data));
    }

    @Test
    public void verifyUserExistenceFalse() throws Exception {
        Student tempUser = new Student("Teste", "novo@uepa.br", "123", "foto");
        assertFalse(data.verifyUserExistence(tempUser, data));
    }

    @Test
    public void verifyUserExistenceNullData() {
        assertThrows(Exception.class, () -> data.verifyUserExistence(student, null));
    }

    @Test
    public void searchStaffFound() {
        Staff found = data.searchStaff(staff.getId());
        assertNotNull(found);
        assertEquals("prof@uepa.br", found.getEmail());
    }

    @Test
    public void searchStaffNotFound() {
        Staff found = data.searchStaff(999);
        assertNull(found);
    }
}