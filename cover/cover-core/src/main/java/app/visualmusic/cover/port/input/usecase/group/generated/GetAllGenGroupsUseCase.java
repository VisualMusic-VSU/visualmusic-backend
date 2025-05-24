package app.visualmusic.cover.port.input.usecase.group.generated;

import app.visualmusic.cover.domain.Group;
import app.visualmusic.cover.port.input.group.generated.GetAllGenGroupsInputPort;
import app.visualmusic.cover.port.input.mapper.GroupMapper;
import app.visualmusic.cover.port.output.AuthServiceOutputPort;
import app.visualmusic.cover.port.output.GroupOutputPort;
import app.visualmusic.cover.shared.dto.PageResponse;
import app.visualmusic.cover.shared.dto.group.GroupItemResponse;
import app.visualmusic.cover.shared.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetAllGenGroupsUseCase implements GetAllGenGroupsInputPort {
    private final GroupOutputPort groupOutputPort;
    private final AuthServiceOutputPort authService;

    private final GroupMapper groupMapper;

    @Override
    public PageResponse<GroupItemResponse> invoke(long userId, int page, int size) {
        if(!authService.existsUserById(userId)) {
            throw new UserNotFoundException(userId);
        }

        PageResponse<Group> coversPage = groupOutputPort
                .findAllGenerated(userId, page, size);

        return groupMapper.toItemPage(coversPage);
    }
}
