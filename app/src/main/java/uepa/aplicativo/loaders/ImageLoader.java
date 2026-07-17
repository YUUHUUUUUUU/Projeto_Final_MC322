package uepa.aplicativo.loaders;

import java.io.InputStream;

import javafx.scene.image.Image;
import uepa.aplicativo.Exceptions.ImageException;

/**
 * Gerenciador utilitário com foco na verificação e validação de carregamento de imagens.
 * Trata o isolamento de fluxos protegendo a renderização gráfica contra formatos corrompidos ou URLs nulas.
 * * @author União de Entidades, Projetos e Atividades
 */
public class ImageLoader {
    
    /**
     * Construtor privado imutável.
     */
    private ImageLoader() {}

    /**
     * Carrega de forma estrita um elemento gráfico a partir de seu caminho relativo em disco.
     * * @param imagePath Caminho absoluto/relativo da imagem.
     * @return Objeto Image carregado e pronto para exibição no ImageView.
     * @throws ImageException Se a imagem não for localizada no caminho.
     * @throws IllegalArgumentException Se a extensão ou o formato binário do arquivo forem inválidos.
     */
    public static Image LoadImage(String imagePath) throws ImageException, IllegalArgumentException{

        InputStream imageStream = ImageLoader.class.getResourceAsStream(imagePath);
        if(imageStream == null) {
            throw new ImageException("Image not found at path: " + imagePath);
        }

        try{
            Image image = new Image(imageStream);
            return image;
        }
        catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("File format is not valid " + imagePath, e);
        }
    }
}