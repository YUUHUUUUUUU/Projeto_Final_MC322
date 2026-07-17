package uepa.aplicativo.extracurricular;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExtracurricularTest {

    @Test
    public void createValid() {
        Extracurricular extra = new Extracurricular(
            "Centro Acadêmico", 
            "Descrição válida do CA", 
            true, 
            "CCSE", 
            "/logo.png", 
            "/banner.png", 
            "http://link.com", 
            "/fxml/ca.fxml"
        );

        assertEquals("Centro Acadêmico", extra.getName());
        assertEquals("Descrição válida do CA", extra.getDescription());
        assertTrue(extra.isOpenToWork());
        assertEquals("true", extra.getIsOpenString());
        assertEquals("CCSE", extra.getInstitute());
        assertEquals("/logo.png", extra.getLogoPath());
        assertEquals("/banner.png", extra.getBannerPath());
        assertEquals("http://link.com", extra.getHyperLink());
        assertEquals("/fxml/ca.fxml", extra.getFxmlPath());
    }

    @Test
    public void descriptionNullOrEmpty() {
        Extracurricular extra = new Extracurricular("Nome", "Desc", true, "Inst", "logo", "banner", "link", "fxml");

        assertThrows(IllegalArgumentException.class, () -> extra.setDescription(""));
        assertThrows(IllegalArgumentException.class, () -> extra.setDescription("   "));
        assertThrows(IllegalArgumentException.class, () -> extra.setDescription(null));
    }

    @Test
    public void descriptionTooLong() {
        Extracurricular extra = new Extracurricular("Nome", "Desc", true, "Inst", "logo", "banner", "link", "fxml");
        
        String longDescription = "a".repeat(301);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> extra.setDescription(longDescription));
        assertEquals("Descrição muito longa (máximo de 1000 caracteres).", exception.getMessage());
    }

    @Test
    public void instituteNullOrEmpty() {
        Extracurricular extra = new Extracurricular("Nome", "Desc", true, "Inst", "logo", "banner", "link", "fxml");

        assertThrows(IllegalArgumentException.class, () -> extra.setInstitute(""));
        assertThrows(IllegalArgumentException.class, () -> extra.setInstitute(null));
    }

    @Test
    public void bannerPathNullOrEmpty() {
        Extracurricular extra = new Extracurricular("Nome", "Desc", true, "Inst", "logo", "banner", "link", "fxml");

        assertThrows(IllegalArgumentException.class, () -> extra.setBannerPath(" "));
    }

    @Test
    public void updateNextIdIncrement() {
        Extracurricular extra1 = new Extracurricular("Nome", "Desc", true, "Inst", "logo", "banner", "link", "fxml");
        
        extra1.updateNextId(50);

        Extracurricular extra2 = new Extracurricular("Nome2", "Desc", true, "Inst", "logo", "banner", "link", "fxml");
        
        assertEquals(51, extra2.getId());
    }
}