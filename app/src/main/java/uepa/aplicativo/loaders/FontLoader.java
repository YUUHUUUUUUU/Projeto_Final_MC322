package uepa.aplicativo.loaders;

import java.io.FileNotFoundException;

import javafx.scene.text.Font;

/**
 * Utilitário responsável pelo gerenciamento tipográfico unificado da interface do sistema.
 * Efetua a carga e injeção assíncrona das fontes personalizadas da família 'Manrope' 
 * para garantir consistência de identidade visual independente do sistema operacional hospedeiro.
 * * @author União de Entidades, Projetos e Atividades
 */
public class FontLoader {
    /* We don't want to instantiate this Object, only use its methods */
    private FontLoader() {}

    /**
     * Executa a tentativa de leitura física e o registro da tipografia customizada via Stream.
     * Captura de forma resiliente falhas caso o arquivo TrueType (.ttf) mude de subdiretório.
     * * @param path Localização relativa do asset de fonte dentro do diretório de resources.
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
     * Encapsulamento central de tipografia da aplicação.
     * Dispara sequencialmente a injeção gráfica de todas as variações de peso da família de fontes Manrope.
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