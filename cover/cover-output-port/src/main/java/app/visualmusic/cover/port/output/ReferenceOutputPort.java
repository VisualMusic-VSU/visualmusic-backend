package app.visualmusic.cover.port.output;

import app.visualmusic.cover.domain.Genre;
import app.visualmusic.cover.domain.Mood;
import app.visualmusic.cover.domain.Style;

import java.util.List;

public interface ReferenceOutputPort {
    List<Genre> getAllGenres();

    List<Style> getAllStyles();

    List<Mood> getAllMoods();

    boolean existsMood(long id);

    boolean existsStyle(long id);

    boolean existsGenres(List<Long> ids);
}
