package uepa.aplicativo.Exceptions;

public class EmailLocalPartCanNotBeEmpty extends Exception{

    public EmailLocalPartCanNotBeEmpty() {
        String message = "Email Local Part Can Not be Empty";
        super(message);
    }
}