package app.visualmusic.cover.port.input.usecase.group.generated;

import app.visualmusic.cover.domain.Cover;
import app.visualmusic.cover.port.input.group.generated.DeleteGenGroupInputPort;
import app.visualmusic.cover.port.output.CoverImageOutputPort;
import app.visualmusic.cover.port.output.CoverOutputPort;
import app.visualmusic.cover.port.output.GroupOutputPort;
import app.visualmusic.cover.shared.exception.GroupNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DeleteGenGroupUseCase implements DeleteGenGroupInputPort {
    private final GroupOutputPort groupOutputPort;
    private final CoverOutputPort coverOutputPort;
    private final CoverImageOutputPort coverImageOutputPort;

    @Override
    public void invoke(long userId, long groupId) {
        if(!groupOutputPort.existsGeneratedById(userId, groupId)) {
            throw new GroupNotFoundException(groupId);
        }

        List<Cover> covers = coverOutputPort.findAllGeneratedByGroupId(userId, groupId);

        groupOutputPort.deleteById(groupId);
        covers.forEach(coverImageOutputPort::delete);
    }
}
