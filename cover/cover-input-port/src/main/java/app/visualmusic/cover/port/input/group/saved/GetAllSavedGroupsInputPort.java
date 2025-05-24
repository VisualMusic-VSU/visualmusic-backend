package app.visualmusic.cover.port.input.group.saved;

import app.visualmusic.cover.shared.dto.PageResponse;
import app.visualmusic.cover.shared.dto.group.GroupItemResponse;

public interface GetAllSavedGroupsInputPort {
    PageResponse<GroupItemResponse> invoke(long userId, int page, int size);
}
