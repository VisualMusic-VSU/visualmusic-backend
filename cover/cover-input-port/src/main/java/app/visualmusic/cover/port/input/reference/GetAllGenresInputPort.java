package app.visualmusic.cover.port.input.reference;

import app.visualmusic.cover.shared.dto.ReferenceItemResponse;

import java.util.List;

public interface GetAllGenresInputPort {
    List<ReferenceItemResponse> invoke();
}
