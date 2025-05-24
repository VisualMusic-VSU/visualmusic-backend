package app.visualmusic.cover.port.output;

import app.visualmusic.cover.domain.Group;
import app.visualmusic.cover.shared.dto.PageResponse;
import app.visualmusic.cover.shared.dto.group.GroupFiltersRequest;
import app.visualmusic.cover.shared.param.GroupSortParams;

import java.util.Optional;

public interface GroupOutputPort {
    PageResponse<Group> findAll(
            int page,
            int size,
            GroupFiltersRequest filters
    );

    PageResponse<Group> findAllPublic(
            Long userId,
            int page,
            int size,
            GroupSortParams sort,
            GroupFiltersRequest filters
    );

    PageResponse<Group> findAllGenerated(long userId, int page, int size);

    PageResponse<Group> findAllSaved(long userId, int page, int size);

    Optional<Group> findGeneratedById(long userId, long groupId);

    Optional<Group> findPublicById(long id);

    Optional<Group> findSavedById(long userId, long groupId);

    long save(Group group);

    void deleteById(long id);

    boolean existsGeneratedById(long userId, long groupId);

    boolean existsSavedById(long userId, long groupId);

    boolean existsById(long id);

    void removeSavedById(long userId, long groupId);
}
