package app.visualmusic.cover.shared.exception;

public class ReferenceItemNotFoundException extends RuntimeException {
    private ReferenceItemNotFoundException(String message) {
        super(message);
    }

    public static ReferenceItemNotFoundException genresNotFound() {
        return new ReferenceItemNotFoundException("Some genres were not found");
    }

    public static ReferenceItemNotFoundException styleNotFound() {
        return new ReferenceItemNotFoundException("Style not found");
    }

    public static ReferenceItemNotFoundException moodNotFound() {
        return new ReferenceItemNotFoundException("Mood not found");
    }
}
