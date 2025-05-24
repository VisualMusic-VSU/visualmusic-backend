package app.visualmusic.cover.port.input.group.pubIic;

import app.visualmusic.cover.shared.dto.PageResponse;
import app.visualmusic.cover.shared.dto.group.GroupItemResponse;
import app.visualmusic.cover.shared.dto.group.GroupFiltersRequest;
import app.visualmusic.cover.shared.param.GroupSortParams;

public interface GetAllPublicGroupsInputPort {
    PageResponse<GroupItemResponse> invoke(
            Long userId,
            int page,
            int size,
            GroupSortParams sort,
            GroupFiltersRequest filters
    );
}
