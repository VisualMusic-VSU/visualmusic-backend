package app.visualmusic.cover.port.input.cover;

public interface DeleteGenCoverInputPort {
    void invoke(long userId, long groupId, long coverId);
}
