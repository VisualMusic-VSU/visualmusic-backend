package app.visualmusic.cover.shared.exception;

public class GroupNotFoundException extends RuntimeException {
    public GroupNotFoundException(long id) {
        super("Group with id=%d not found".formatted(id));
    }
}
