package app.visualmusic.cover.port.input.group;

import app.visualmusic.cover.shared.dto.PageResponse;
import app.visualmusic.cover.shared.dto.group.GroupItemResponse;
import app.visualmusic.cover.shared.dto.group.GroupFiltersRequest;

public interface GetAllGroupsInputPort {
    PageResponse<GroupItemResponse> invoke(
            int page, int size,
            GroupFiltersRequest filters
    );
}
