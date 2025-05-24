package app.visualmusic.cover.port.input.group.generated;

import app.visualmusic.cover.shared.dto.PageResponse;
import app.visualmusic.cover.shared.dto.group.GroupItemResponse;

public interface GetAllGenGroupsInputPort {
    PageResponse<GroupItemResponse> invoke(long userId, int page, int size);
}
