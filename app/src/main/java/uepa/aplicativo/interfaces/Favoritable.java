package uepa.aplicativo.interfaces;

import uepa.aplicativo.user.Student;

public interface Favoritable {
    
    void addtoFavorite(Student s);
    void removefromFavorite(Student s);
    boolean isFavoriteof(Student s);

}
