package app.visualmusic.cover.port.output;

public interface CoverOutputPort {
    void deleteById(long id);

    boolean existsGeneratedById(long userId, long groupId, long coverId);

    boolean existsById(long groupId, long coverId);
}
