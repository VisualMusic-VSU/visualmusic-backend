package app.visualmusic.cover.port.input.usecase.cover;

import app.visualmusic.cover.port.input.cover.DeleteCoverInputPort;
import app.visualmusic.cover.port.output.CoverOutputPort;
import app.visualmusic.cover.shared.exception.CoverNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteCoverUseCase implements DeleteCoverInputPort {
    private final CoverOutputPort coverOutputPort;

    @Override
    public void invoke(long groupId, long coverId) {
        if(!coverOutputPort.existsById(groupId, coverId)) {
            throw new CoverNotFoundException(groupId);
        }

        coverOutputPort.deleteById(coverId);
    }
}
