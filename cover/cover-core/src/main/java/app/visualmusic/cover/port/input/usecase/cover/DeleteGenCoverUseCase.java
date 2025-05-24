package app.visualmusic.cover.port.input.usecase.cover;

import app.visualmusic.cover.port.input.cover.DeleteGenCoverInputPort;
import app.visualmusic.cover.port.output.CoverOutputPort;
import app.visualmusic.cover.port.output.GroupOutputPort;
import app.visualmusic.cover.shared.exception.CoverNotFoundException;
import app.visualmusic.cover.shared.exception.GroupNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteGenCoverUseCase implements DeleteGenCoverInputPort {
    private final GroupOutputPort groupOutputPort;
    private final CoverOutputPort coverOutputPort;

    @Override
    public void invoke(long userId, long groupId, long coverId) {
        if (!groupOutputPort.existsGeneratedById(userId, groupId)) {
            throw new GroupNotFoundException(groupId);
        }

        if (!coverOutputPort.existsGeneratedById(userId, groupId, coverId)) {
            throw new CoverNotFoundException(coverId);
        }

        coverOutputPort.deleteById(coverId);
    }
}
