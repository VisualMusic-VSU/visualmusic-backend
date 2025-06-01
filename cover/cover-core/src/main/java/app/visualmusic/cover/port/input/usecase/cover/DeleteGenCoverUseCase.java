package app.visualmusic.cover.port.input.usecase.cover;

import app.visualmusic.cover.domain.Cover;
import app.visualmusic.cover.port.input.cover.DeleteGenCoverInputPort;
import app.visualmusic.cover.port.output.CoverImageOutputPort;
import app.visualmusic.cover.port.output.CoverOutputPort;
import app.visualmusic.cover.port.output.GroupOutputPort;
import app.visualmusic.cover.shared.exception.CoverNotFoundException;
import app.visualmusic.cover.shared.exception.GroupNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteGenCoverUseCase implements DeleteGenCoverInputPort {
    private final GroupOutputPort groupOutputPort;
    private final CoverOutputPort coverOutputPort;
    private final CoverImageOutputPort coverImageOutputPort;

    @Override
    public void invoke(long userId, long groupId, long coverId) {
        if (!groupOutputPort.existsGeneratedById(userId, groupId)) {
            throw new GroupNotFoundException(groupId);
        }

        Cover cover = coverOutputPort.findGeneratedById(userId, groupId, coverId)
                .orElseThrow(() -> new CoverNotFoundException(groupId));

        coverOutputPort.deleteById(coverId);
        coverImageOutputPort.delete(cover);
    }
}
