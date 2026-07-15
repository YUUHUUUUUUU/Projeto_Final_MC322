package uepa.aplicativo.interfaces;

import uepa.aplicativo.user.User;

public interface Favoritable {
    
    void addToFavorite(User u);
    void removeFromFavorite(User u);
    boolean isFavoriteof(User u);
}
