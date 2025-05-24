package app.visualmusic.cover.port.input.cover;

public interface DeleteCoverInputPort {
    void invoke(long groupId, long coverId);
}
