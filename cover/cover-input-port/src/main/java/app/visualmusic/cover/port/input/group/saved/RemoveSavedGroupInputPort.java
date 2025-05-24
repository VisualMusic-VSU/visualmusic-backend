package app.visualmusic.cover.port.input.group.saved;

public interface RemoveSavedGroupInputPort {
    void invoke(long userId, long groupId);
}
