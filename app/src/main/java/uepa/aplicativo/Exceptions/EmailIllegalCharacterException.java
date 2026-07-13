package uepa.aplicativo.Exceptions;

public class EmailIllegalCharacterException extends Exception {
    public EmailIllegalCharacterException() {
        String message = "Email contains illegal characters";
        super(message);
    }
}
