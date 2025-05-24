package app.visualmusic.cover.port.input.usecase.reference;

import app.visualmusic.cover.port.input.reference.GetAllMoodsInputPort;
import app.visualmusic.cover.port.input.mapper.ReferenceMapper;
import app.visualmusic.cover.port.output.ReferenceOutputPort;
import app.visualmusic.cover.shared.dto.ReferenceItemResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllMoodsUseCase implements GetAllMoodsInputPort {
    private final ReferenceOutputPort referenceOutputPort;
    private final ReferenceMapper mapper;

    @Override
    public List<ReferenceItemResponse> invoke() {
        return referenceOutputPort.getAllMoods()
                .stream()
                .map(mapper::toItem)
                .toList();
    }
}
