package app.visualmusic.cover.port.input.group.pubIic;

import app.visualmusic.cover.shared.dto.group.GroupDetailResponse;

public interface GetPublicGroupInputPort {
    GroupDetailResponse invoke(long groupId);
}
