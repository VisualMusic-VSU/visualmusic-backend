package app.visualmusic.cover.port.output;

import app.visualmusic.cover.domain.Cover;

public interface CoverImageOutputPort {

    String getUrl(Cover cover);

    void delete(Cover cover);
}
