package app.visualmusic.cover.port.input.spring.web;

import app.visualmusic.cover.shared.dto.PageResponse;
import app.visualmusic.cover.shared.dto.group.GroupDetailResponse;
import app.visualmusic.cover.shared.dto.group.GroupItemResponse;
import app.visualmusic.cover.shared.dto.group.GroupFiltersRequest;
import app.visualmusic.cover.shared.param.GroupSortParams;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping(
        value = "/api/v1/groups",
        produces = "application/json"
)
public interface GroupRestApi {

    @PostMapping
    ResponseEntity<PageResponse<GroupItemResponse>> getAllGroups(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestBody(required = false) GroupFiltersRequest filters
    );

    @DeleteMapping(value = "/{groupId:\\d+}")
    ResponseEntity<Void> deleteGroup(@PathVariable("groupId") long groupId);

    @PostMapping(value = "/public")
    ResponseEntity<PageResponse<GroupItemResponse>> getAllPublicGroups(
            @AuthenticationPrincipal Long userId,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "POPULAR") GroupSortParams sort,
            @RequestBody(required = false) GroupFiltersRequest filters
    );

    @GetMapping(value = "/public/{groupId:\\d+}")
    ResponseEntity<GroupDetailResponse> getPublicCoverGroup(
            @PathVariable("groupId") long groupId
    );

    @GetMapping(value = "/generated")
    ResponseEntity<PageResponse<GroupItemResponse>> getAllGenGroups(
            @AuthenticationPrincipal long userId,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size
    );

    @GetMapping(value = "/generated/{groupId:\\d+}")
    ResponseEntity<GroupDetailResponse> getGenCoverGroup(
            @AuthenticationPrincipal long userId,
            @PathVariable("groupId") long groupId
    );

    @DeleteMapping(value = "/generated/{groupId:\\d+}")
    ResponseEntity<Void> deleteGenCoverGroup(
            @AuthenticationPrincipal long userId,
            @PathVariable("groupId") long groupId
    );

    @GetMapping(value = "/saved")
    ResponseEntity<PageResponse<GroupItemResponse>> getAllSavedGroups(
            @AuthenticationPrincipal long userId,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size
    );

    @GetMapping(value = "/saved/{groupId:\\d+}")
    ResponseEntity<GroupDetailResponse> getSavedGroup(
            @AuthenticationPrincipal long userId,
            @PathVariable("groupId") long groupId
    );

    @DeleteMapping(value = "/saved/{groupId:\\d+}")
    ResponseEntity<Void> removeSavedGroup(
            @AuthenticationPrincipal long userId,
            @PathVariable("groupId") long groupId
    );
}
