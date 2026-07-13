package uepa.aplicativo.loaders;

import java.io.FileNotFoundException;

import javafx.scene.text.Font;

public class FontLoader {
    /* We don't want to instantiate this Object, only use its methods */
    private FontLoader() {}

    /**
     * Represents the actual method of loading fonts, this method is the
     * back-end code behind of {@link #loadFonts()}
     * 
     * @param path represents the path of the font, the path is relative to /app/src/main/resources/
     * 
     * @author Enzo Farina Mullis
     */
    private static void tryLoadingFont(String path){
        try{
            Font customFont = Font.loadFont(FontLoader.class.getResourceAsStream(path), 12);
            if(customFont == null) {
                throw new FileNotFoundException("Font not found: " + path);
            }
            System.out.println("Loaded font: " + customFont.getName() + " (family: " + customFont.getFamily() + ")");
        }
        catch(FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException);
        }
    }

    /**
     * Encapsulation of the font loader
     * 
     * <p>
     * This method encapsulates the method {@link #tryLoadingFont}
     * </p>
     * 
     * @author Enzo Farina Mullis
     */
    public static void loadFonts(){
        
        tryLoadingFont("/fonts/Manrope-Bold.ttf");
        tryLoadingFont("/fonts/Manrope-ExtraBold.ttf");
        tryLoadingFont("/fonts/Manrope-Light.ttf");
        tryLoadingFont("/fonts/Manrope-ExtraLight.ttf");
        tryLoadingFont("/fonts/Manrope-Regular.ttf");
        tryLoadingFont("/fonts/Manrope-Medium.ttf");
        tryLoadingFont("/fonts/Manrope-SemiBold.ttf");
    }
}
