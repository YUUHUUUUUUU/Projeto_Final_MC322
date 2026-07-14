package uepa.aplicativo.extracurricular.gallery;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import uepa.aplicativo.loaders.ImageLoader;

public class PhotoGallery {
    private Image logo;
    private List<Image> photoBook;

    public PhotoGallery () {
        List<Image> photos = new ArrayList<>();
    }

    public Image getLogo() {
        return logo;
    }

    public List<Image> getPhotoBook() {
        ruturn photoBook;
    }

    public void setLogo(String logoPath) throws IllegalArgumentException{
        try {
            logo = ImageLoader.LoadImage(logoPath);
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addPhoto(String photoPath) {
        try {
            photoBook.add(ImageLoader.LoadImage(photoPath));
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

}
