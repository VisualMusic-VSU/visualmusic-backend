package app.visualmusic.cover.port.input.usecase.group.generated;

import app.visualmusic.cover.domain.Group;
import app.visualmusic.cover.port.input.group.generated.GetGenGroupInputPort;
import app.visualmusic.cover.port.input.mapper.GroupMapper;
import app.visualmusic.cover.port.output.GroupOutputPort;
import app.visualmusic.cover.shared.dto.group.GroupDetailResponse;
import app.visualmusic.cover.shared.exception.GroupNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetGenGroupUseCase implements GetGenGroupInputPort {
    private final GroupOutputPort groupOutputPort;

    private final GroupMapper groupMapper;

    @Override
    public GroupDetailResponse invoke(long userId, long groupId) {
        Group group = groupOutputPort.findGeneratedById(userId, groupId)
                .orElseThrow(() -> new GroupNotFoundException(groupId));

        return groupMapper.toDetail(group);
    }
}
