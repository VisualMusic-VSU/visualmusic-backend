package app.visualmusic.cover.port.input.usecase.group.saved;

import app.visualmusic.cover.domain.Group;
import app.visualmusic.cover.port.input.group.saved.GetSavedGroupInputPort;
import app.visualmusic.cover.port.input.mapper.GroupMapper;
import app.visualmusic.cover.port.output.GroupOutputPort;
import app.visualmusic.cover.shared.dto.group.GroupDetailResponse;
import app.visualmusic.cover.shared.exception.GroupNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetSavedGroupUseCase implements GetSavedGroupInputPort {
    private final GroupOutputPort groupOutputPort;

    private final GroupMapper groupMapper;

    @Override
    public GroupDetailResponse invoke(long userId, long groupId) {
        Group group = groupOutputPort.findSavedById(userId, groupId)
                .orElseThrow(() -> new GroupNotFoundException(groupId));
        return groupMapper.toDetail(group);
    }
}
