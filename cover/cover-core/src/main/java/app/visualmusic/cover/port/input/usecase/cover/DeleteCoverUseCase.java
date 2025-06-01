package app.visualmusic.cover.port.input.usecase.cover;

import app.visualmusic.cover.domain.Cover;
import app.visualmusic.cover.port.input.cover.DeleteCoverInputPort;
import app.visualmusic.cover.port.output.CoverImageOutputPort;
import app.visualmusic.cover.port.output.CoverOutputPort;
import app.visualmusic.cover.shared.exception.CoverNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteCoverUseCase implements DeleteCoverInputPort {
    private final CoverOutputPort coverOutputPort;
    private final CoverImageOutputPort coverImageOutputPort;

    @Override
    public void invoke(long groupId, long coverId) {
        Cover cover = coverOutputPort.findById(groupId, coverId)
                .orElseThrow(() -> new CoverNotFoundException(groupId));

        coverOutputPort.deleteById(coverId);
        coverImageOutputPort.delete(cover);
    }
}
