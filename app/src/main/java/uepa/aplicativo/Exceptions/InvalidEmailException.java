package uepa.aplicativo.Exceptions;

/**
 * Exceção validativa (Fail-Fast) acionada pelo UserManager sempre que um e-mail 
 * não obedece as normas rígidas da internet e as validações sintáticas das expressões regulares.
 * * @author União de Entidades, Projetos e Atividades
 */
public class InvalidEmailException extends Exception {
    public InvalidEmailException(String message) {
        super(message);
    }
}