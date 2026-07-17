package uepa.aplicativo.testes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import uepa.aplicativo.extracurricular.Extracurricular;
import uepa.aplicativo.user.Staff;
import uepa.aplicativo.interfaces.notifiable;
import uepa.aplicativo.message.Message;

public class ExtracurricularListsTest {

    private Extracurricular extra;
    private Staff staff1;
    private Staff staff2;
    private notifiable listener1;
    private notifiable listener2;

    private class ExtracurricularConcrete extends Extracurricular {
        public ExtracurricularConcrete(String name, String description, boolean openToWork, String institute, String bannerPath, String logoPath, String hyperLink) {
            super(name, description, openToWork, institute, bannerPath, logoPath, hyperLink);
        }
    }

    private class StaffConcrete extends Staff {
        public StaffConcrete(String email, String name, String photoPath) {
            super(email, name, photoPath);
        }
    }

    private class StudentListener implements notifiable {
        @Override
        public void receiveMessage(Message message) {
        }
    }

    @BeforeEach
    public void setUp() {
        extra = new ExtracurricularConcrete("Projeto Computação", "Dev de Apps", true, "CCNT", "/b.png", "/l.png", "link.br");
        staff1 = new StaffConcrete("prof1@uepa.br", "Professor João", "/p1.png");
        staff2 = new StaffConcrete("prof2@uepa.br", "Professora Maria", "/p2.png");
        listener1 = new StudentListener();
        listener2 = new StudentListener();
    }

    @Test
    public void testAdicionarStaff() {
        extra.addStaff(staff1);
        extra.addStaff(staff2);

        assertEquals(2, extra.getStaffList().size());
        assertEquals(staff1, extra.getStaffList().get(0));
        assertEquals(staff2, extra.getStaffList().get(1));
    }

    @Test
    public void testAdicionarStaffDuplicado() {
        extra.addStaff(staff1);
        extra.addStaff(staff1);

        assertEquals(1, extra.getStaffList().size());
    }

    @Test
    public void testRemoverStaff() {
        extra.addStaff(staff1);
        extra.addStaff(staff2);
        extra.removeStaff(staff1);

        assertEquals(1, extra.getStaffList().size());
        assertFalse(extra.getStaffList().contains(staff1));
        assertTrue(extra.getStaffList().contains(staff2));
    }

    @Test
    public void testCompararListaDeStaff() {
        List<Staff> listaEsperada = new ArrayList<>();
        listaEsperada.add(staff1);
        listaEsperada.add(staff2);

        extra.addStaff(staff1);
        extra.addStaff(staff2);

        assertEquals(listaEsperada, extra.getStaffList());
        assertIterableEquals(listaEsperada, extra.getStaffList());
    }

    @Test
    public void testAdicionarERemoverListeners() {
        extra.addListener(listener1);
        extra.addListener(listener2);
        assertEquals(2, extra.getUsersListeners().size());

        extra.removeListener(listener1);
        assertEquals(1, extra.getUsersListeners().size());
        assertFalse(extra.getUsersListeners().contains(listener1));
    }

    @Test
    public void testAdicionarListenerDuplicado() {
        extra.addListener(listener1);
        extra.addListener(listener1);

        assertEquals(1, extra.getUsersListeners().size());
    }
}