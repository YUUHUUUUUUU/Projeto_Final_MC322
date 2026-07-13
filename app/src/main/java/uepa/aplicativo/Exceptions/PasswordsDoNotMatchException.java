package uepa.aplicativo.Exceptions;

public class PasswordsDoNotMatchException extends Exception{
    public PasswordsDoNotMatchException() {
        String message = "Passwords Do Not Match";
        super(message);
    }
}
