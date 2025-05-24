package app.visualmusic.cover.port.input.usecase.reference;

import app.visualmusic.cover.port.input.reference.GetAllGenresInputPort;
import app.visualmusic.cover.port.input.mapper.ReferenceMapper;
import app.visualmusic.cover.port.output.ReferenceOutputPort;
import app.visualmusic.cover.shared.dto.ReferenceItemResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllGenresUseCase implements GetAllGenresInputPort {
    private final ReferenceOutputPort referenceOutputPort;
    private final ReferenceMapper mapper;

    @Override
    public List<ReferenceItemResponse> invoke() {
        return referenceOutputPort.getAllGenres()
                .stream()
                .map(mapper::toItem)
                .toList();
    }
}
