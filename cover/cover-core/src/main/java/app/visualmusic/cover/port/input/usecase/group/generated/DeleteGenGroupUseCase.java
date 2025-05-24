package app.visualmusic.cover.port.input.usecase.group.generated;

import app.visualmusic.cover.port.input.group.generated.DeleteGenGroupInputPort;
import app.visualmusic.cover.port.output.GroupOutputPort;
import app.visualmusic.cover.shared.exception.GroupNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteGenGroupUseCase implements DeleteGenGroupInputPort {
    private final GroupOutputPort groupOutputPort;

    @Override
    public void invoke(long userId, long groupId) {
        if(!groupOutputPort.existsGeneratedById(userId, groupId)) {
            throw new GroupNotFoundException(groupId);
        }

        groupOutputPort.deleteById(groupId);
    }
}
