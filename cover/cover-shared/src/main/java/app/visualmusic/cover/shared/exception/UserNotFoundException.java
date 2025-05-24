package app.visualmusic.cover.shared.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(long id) {
        super("User with id=%s not found".formatted(id));
    }
}
