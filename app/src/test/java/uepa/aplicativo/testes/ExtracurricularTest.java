package uepa.aplicativo.testes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import uepa.aplicativo.extracurricular.Extracurricular;

public class ExtracurricularTest {

    private Extracurricular extra;

    private class ExtracurricularConcrete extends Extracurricular {
        public ExtracurricularConcrete(String name, String description, boolean openToWork, String institute, String bannerPath, String logoPath, String hyperLink) {
            super(name, description, openToWork, institute, bannerPath, logoPath, hyperLink);
        }
    }

    @BeforeEach
    public void setUp() {
        extra = new ExtracurricularConcrete("Clube de IA", "Estudos em IA", true, "CCNT", "/banner.png", "/logo.png", "http://uepa.br/ia");
    }

    @Test
    public void testGettersInicializacao() {
        assertEquals("Clube de IA", extra.getName());
        assertEquals("Estudos em IA", extra.getDescription());
        assertTrue(extra.isOpenToWork());
        assertEquals("CCNT", extra.getInstitute());
        assertEquals("/banner.png", extra.getBannerPath());
        assertEquals("/logo.png", extra.getLogoPath());
        assertEquals("http://uepa.br/ia", extra.getHyperLink());
    }

    @Test
    public void testSetName() {
        extra.setName("Laboratório de Robótica");
        assertEquals("Laboratório de Robótica", extra.getName());
    }

    @Test
    public void testSetDescription() {
        extra.setDescription("Desenvolvimento de protótipos autônomos");
        assertEquals("Desenvolvimento de protótipos autônomos", extra.getDescription());
    }

    @Test
    public void testSetOpenToWork() {
        extra.setOpenToWork(false);
        assertFalse(extra.isOpenToWork());
    }

    @Test
    public void testSetInstitute() {
        extra.setInstitute("CCBS");
        assertEquals("CCBS", extra.getInstitute());
    }

    @Test
    public void testSetBannerPathAndLogoPath() {
        extra.setBannerPath("/imagens/novo_banner.png");
        extra.setLogoPath("/imagens/nova_logo.png");
        assertEquals("/imagens/novo_banner.png", extra.getBannerPath());
        assertEquals("/imagens/nova_logo.png", extra.getLogoPath());
    }

    @Test
    public void testSetHyperLink() {
        extra.setHyperLink("http://roboticauepa.br");
        assertEquals("http://roboticauepa.br", extra.getHyperLink());
    }
}