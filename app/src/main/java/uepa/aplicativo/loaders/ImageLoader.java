package uepa.aplicativo.loaders;

import java.io.InputStream;

import javafx.scene.image.Image;
import uepa.aplicativo.Exceptions.ImageException;

public class ImageLoader {
    private ImageLoader() {}

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
