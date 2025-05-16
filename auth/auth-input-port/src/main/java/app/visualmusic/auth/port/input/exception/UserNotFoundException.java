package app.visualmusic.auth.port.input.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String email) {
        super("User with email=%s not found".formatted(email));
    }

    public UserNotFoundException(Long id) {
        super("User with id=%s not found".formatted(id));
    }
}
