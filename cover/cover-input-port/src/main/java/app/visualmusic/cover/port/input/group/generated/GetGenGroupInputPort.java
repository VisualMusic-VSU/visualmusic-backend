package app.visualmusic.cover.port.input.group.generated;

import app.visualmusic.cover.shared.dto.group.GroupDetailResponse;

public interface GetGenGroupInputPort {
    GroupDetailResponse invoke(long userId, long groupId);
}
