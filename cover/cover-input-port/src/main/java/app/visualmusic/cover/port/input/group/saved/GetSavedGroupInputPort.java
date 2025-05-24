package app.visualmusic.cover.port.input.group.saved;

import app.visualmusic.cover.shared.dto.group.GroupDetailResponse;

public interface GetSavedGroupInputPort {
    GroupDetailResponse invoke(long userId, long groupId);
}
