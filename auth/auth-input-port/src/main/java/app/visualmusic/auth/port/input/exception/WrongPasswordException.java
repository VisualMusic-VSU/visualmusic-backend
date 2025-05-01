package app.visualmusic.auth.port.input.exception;

public class WrongPasswordException extends RuntimeException {

    public WrongPasswordException(String email) {
        super("Wrong password for user with email=%s".formatted(email));
    }
}
