package app.visualmusic.cover.port.input.reference;

import app.visualmusic.cover.shared.dto.ReferenceItemResponse;

import java.util.List;

public interface GetAllStylesInputPort {
    List<ReferenceItemResponse> invoke();
}
