package app.visualmusic.cover.port.output;

import app.visualmusic.cover.domain.Cover;

import java.util.List;
import java.util.Optional;

public interface CoverOutputPort {
    void deleteById(long id);

    Optional<Cover> findGeneratedById(long userId, long groupId, long coverId);

    List<Cover> findAllGeneratedByGroupId(long userId, long groupId);

    Optional<Cover> findById(long groupId, long coverId);

    List<Cover> findAllByGroupId(long groupId);
}
