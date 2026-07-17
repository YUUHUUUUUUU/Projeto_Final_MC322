package uepa.aplicativo.Exceptions;

/**
 * Exceção de segurança disparada caso o usuário submeta durante o registro senhas
 * frágeis, que descumpram o comprimento ou não possuam símbolos alfanuméricos complexos.
 * * @author União de Entidades, Projetos e Atividades
 */
public class InvalidPasswordException extends Exception{
    public InvalidPasswordException(String message) {
        super(message);
    }
}