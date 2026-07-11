package uepa.aplicativo.usuarios;
public interface Favoritable {
    void addtoFavorite(Student s);
    void removefromFavorite(Student s);
    boolean isFavoriteof(Student s);

}
