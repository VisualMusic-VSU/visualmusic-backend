package app.visualmusic.cover.port.input.spring.web.controller;

import app.visualmusic.cover.port.input.group.DeleteGroupInputPort;
import app.visualmusic.cover.port.input.group.GetAllGroupsInputPort;
import app.visualmusic.cover.port.input.group.generated.DeleteGenGroupInputPort;
import app.visualmusic.cover.port.input.group.generated.GetAllGenGroupsInputPort;
import app.visualmusic.cover.port.input.group.generated.GetGenGroupInputPort;
import app.visualmusic.cover.port.input.group.pubIic.GetAllPublicGroupsInputPort;
import app.visualmusic.cover.port.input.group.pubIic.GetPublicGroupInputPort;
import app.visualmusic.cover.port.input.group.saved.GetAllSavedGroupsInputPort;
import app.visualmusic.cover.port.input.group.saved.GetSavedGroupInputPort;
import app.visualmusic.cover.port.input.group.saved.RemoveSavedGroupInputPort;
import app.visualmusic.cover.port.input.spring.web.GroupRestApi;
import app.visualmusic.cover.shared.dto.PageResponse;
import app.visualmusic.cover.shared.dto.group.GroupDetailResponse;
import app.visualmusic.cover.shared.dto.group.GroupFiltersRequest;
import app.visualmusic.cover.shared.dto.group.GroupItemResponse;
import app.visualmusic.cover.shared.param.GroupSortParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GroupRestController implements GroupRestApi {
    private final GetAllPublicGroupsInputPort getAllPublicGroupsInputPort;
    private final GetAllGenGroupsInputPort getAllGenGroupsInputPort;
    private final GetAllSavedGroupsInputPort getAllSavedGroupsInputPort;
    private final GetAllGroupsInputPort getAllGroupsInputPort;

    private final GetPublicGroupInputPort getPublicGroupInputPort;
    private final GetGenGroupInputPort getGenGroupInputPort;
    private final GetSavedGroupInputPort getSavedGroupInputPort;

    private final DeleteGenGroupInputPort deleteGenGroupInputPort;
    private final DeleteGroupInputPort deleteGroupInputPort;
    private final RemoveSavedGroupInputPort removeSavedGroupInputPort;

    @Override
    public ResponseEntity<PageResponse<GroupItemResponse>> getAllGroups(
            int page, int size,
            GroupFiltersRequest filters
    ) {
        var result = getAllGroupsInputPort.invoke(page, size, filters);

        return result.getContent().isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> deleteGroup(long groupId) {
        deleteGroupInputPort.invoke(groupId);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<PageResponse<GroupItemResponse>> getAllPublicGroups(
            Long userId,
            int page, int size,
            GroupSortParams sort,
            GroupFiltersRequest filters
    ) {
        var result = getAllPublicGroupsInputPort.invoke(userId, page, size, sort, filters);

        return result.getContent().isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<GroupDetailResponse> getPublicCoverGroup(long groupId) {
        var result = getPublicGroupInputPort.invoke(groupId);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<PageResponse<GroupItemResponse>> getAllGenGroups(
            long userId,
            int page, int size
    ) {
        var result = getAllGenGroupsInputPort.invoke(userId, page, size);

        return result.getContent().isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<GroupDetailResponse> getGenCoverGroup(long userId, long groupId) {
        var result = getGenGroupInputPort.invoke(userId, groupId);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> deleteGenCoverGroup(long userId, long groupId) {
        deleteGenGroupInputPort.invoke(userId, groupId);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<PageResponse<GroupItemResponse>> getAllSavedGroups(
            long userId,
            int page, int size
    ) {
        var result = getAllSavedGroupsInputPort.invoke(userId, page, size);

        return result.getContent().isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<GroupDetailResponse> getSavedGroup(long userId, long groupId) {
        var result = getSavedGroupInputPort.invoke(userId, groupId);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> removeSavedGroup(long userId, long groupId) {
        removeSavedGroupInputPort.invoke(userId, groupId);
        return ResponseEntity.ok().build();
    }
}
