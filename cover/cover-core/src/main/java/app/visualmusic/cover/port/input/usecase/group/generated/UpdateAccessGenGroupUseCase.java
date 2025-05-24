package app.visualmusic.cover.port.input.usecase.group.generated;

import app.visualmusic.cover.domain.Group;
import app.visualmusic.cover.port.input.group.generated.UpdateAccessGenGroupInputPort;
import app.visualmusic.cover.port.output.AuthServiceOutputPort;
import app.visualmusic.cover.port.output.GroupOutputPort;
import app.visualmusic.cover.shared.exception.GroupNotFoundException;
import app.visualmusic.cover.shared.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateAccessGenGroupUseCase implements UpdateAccessGenGroupInputPort {
    private final GroupOutputPort groupOutputPort;
    private final AuthServiceOutputPort authService;

    @Override
    public void invoke(long userId, long groupId, boolean isPrivate) {
        if (!authService.existsUserById(userId)) {
            throw new UserNotFoundException(userId);
        }

        Group group = groupOutputPort.findGeneratedById(userId, groupId)
                .orElseThrow(() -> new GroupNotFoundException(groupId));
        group.setPrivate(isPrivate);

        groupOutputPort.save(group);
    }
}
