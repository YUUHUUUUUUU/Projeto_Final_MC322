package uepa.aplicativo.Exceptions;

public class EmailLocalPartIsTooLong extends Exception {
    
    public EmailLocalPartIsTooLong() {
        String message = "Email Local Part Is Too Long";
        super(message);
    }
}
