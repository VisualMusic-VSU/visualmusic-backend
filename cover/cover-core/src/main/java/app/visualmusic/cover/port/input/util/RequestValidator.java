package app.visualmusic.cover.port.input.util;

import app.visualmusic.cover.port.output.ReferenceOutputPort;
import app.visualmusic.cover.shared.dto.group.GroupFiltersRequest;
import app.visualmusic.cover.shared.exception.ReferenceItemNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RequestValidator {
    private final ReferenceOutputPort referenceOutputPort;

    public void validateFilters(GroupFiltersRequest filters) {
        if (filters == null) {
            return;
        }

        Long moodId = filters.getMoodId();
        Long styleId = filters.getStyleId();
        List<Long> genreIds = filters.getGenreIds();

        if (moodId != null && !referenceOutputPort.existsMood(moodId)) {
            throw ReferenceItemNotFoundException.moodNotFound();
        }

        if (styleId != null && !referenceOutputPort.existsStyle(styleId)) {
            throw ReferenceItemNotFoundException.styleNotFound();
        }

        if (!genreIds.isEmpty() && !referenceOutputPort.existsGenres(genreIds)) {
            throw ReferenceItemNotFoundException.genresNotFound();
        }
    }
}
