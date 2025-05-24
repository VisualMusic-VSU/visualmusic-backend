package app.visualmusic.cover.shared.exception;

public class CoverNotFoundException extends RuntimeException {
    public CoverNotFoundException(long id) {
        super("Cover with id=%d not found".formatted(id));
    }
}
