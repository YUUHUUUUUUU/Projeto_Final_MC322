package uepa.aplicativo.Exceptions;

/**
 * Exceção disparada quando os gerenciadores estáticos (Loaders) não conseguem 
 * localizar fisicamente ou renderizar um asset de imagem/banner requerido pela interface.
 * * @author União de Entidades, Projetos e Atividades
 */
public class ImageException extends Exception {
    public ImageException(String message) {
        super(message);
    }
}