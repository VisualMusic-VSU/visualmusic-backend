package app.visualmusic.cover.port.input.usecase.group;

import app.visualmusic.cover.domain.Cover;
import app.visualmusic.cover.port.input.group.DeleteGroupInputPort;
import app.visualmusic.cover.port.output.CoverImageOutputPort;
import app.visualmusic.cover.port.output.CoverOutputPort;
import app.visualmusic.cover.port.output.GroupOutputPort;
import app.visualmusic.cover.shared.exception.GroupNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DeleteGroupUseCase implements DeleteGroupInputPort {
    private final GroupOutputPort groupOutputPort;
    private final CoverOutputPort coverOutputPort;
    private final CoverImageOutputPort coverImageOutputPort;

    @Override
    public void invoke(long groupId) {
        if(!groupOutputPort.existsById(groupId)) {
            throw new GroupNotFoundException(groupId);
        }

        List<Cover> covers = coverOutputPort.findAllByGroupId(groupId);

        groupOutputPort.deleteById(groupId);
        covers.forEach(coverImageOutputPort::delete);
    }
}
