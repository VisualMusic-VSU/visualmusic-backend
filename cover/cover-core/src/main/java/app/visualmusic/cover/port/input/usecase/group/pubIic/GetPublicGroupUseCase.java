package app.visualmusic.cover.port.input.usecase.group.pubIic;

import app.visualmusic.cover.domain.Group;
import app.visualmusic.cover.port.input.group.pubIic.GetPublicGroupInputPort;
import app.visualmusic.cover.port.input.mapper.GroupMapper;
import app.visualmusic.cover.port.output.GroupOutputPort;
import app.visualmusic.cover.shared.dto.group.GroupDetailResponse;
import app.visualmusic.cover.shared.exception.GroupNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetPublicGroupUseCase implements GetPublicGroupInputPort {
    private final GroupOutputPort groupOutputPort;

    private final GroupMapper groupMapper;

    @Override
    public GroupDetailResponse invoke(long groupId) {
        Group group = groupOutputPort.findPublicById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(groupId));
        return groupMapper.toDetail(group);
    }
}
