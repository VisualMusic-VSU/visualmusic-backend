package app.visualmusic.cover.port.input.usecase.group.saved;

import app.visualmusic.cover.port.input.group.saved.RemoveSavedGroupInputPort;
import app.visualmusic.cover.port.output.GroupOutputPort;
import app.visualmusic.cover.shared.exception.GroupNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RemoveSavedGroupUseCase implements RemoveSavedGroupInputPort {
    private final GroupOutputPort groupOutputPort;

    @Override
    public void invoke(long userId, long groupId) {
        if(!groupOutputPort.existsSavedById(userId, groupId)) {
            throw new GroupNotFoundException(groupId);
        }

        groupOutputPort.removeSavedById(userId, groupId);
    }
}
