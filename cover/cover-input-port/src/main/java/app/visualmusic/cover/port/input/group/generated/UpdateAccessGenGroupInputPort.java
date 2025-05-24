package app.visualmusic.cover.port.input.group.generated;

public interface UpdateAccessGenGroupInputPort {
    void invoke(long userId, long groupId, boolean isPrivate);
}
