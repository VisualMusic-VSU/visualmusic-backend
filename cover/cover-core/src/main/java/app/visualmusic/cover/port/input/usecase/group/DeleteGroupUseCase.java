package app.visualmusic.cover.port.input.usecase.group;

import app.visualmusic.cover.port.input.group.DeleteGroupInputPort;
import app.visualmusic.cover.port.output.GroupOutputPort;
import app.visualmusic.cover.shared.exception.GroupNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteGroupUseCase implements DeleteGroupInputPort {
    private final GroupOutputPort groupOutputPort;

    @Override
    public void invoke(long groupId) {
        if(!groupOutputPort.existsById(groupId)) {
            throw new GroupNotFoundException(groupId);
        }

        groupOutputPort.deleteById(groupId);
    }
}
