package app.visualmusic.cover.port.input.mapper;

import app.visualmusic.cover.domain.Cover;
import app.visualmusic.cover.shared.dto.group.CoverItemData;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CoverMapperTest {

    private final CoverMapper mapper = CoverMapper.INSTANCE;

    @Test
    void toItemData_shouldMapImageUrl() {

        Cover cover = mock(Cover.class);

        Function<Cover, String> imageUrlProvider = c -> "http://example.com/image.jpg";

        CoverItemData dto = mapper.toItemData(cover, imageUrlProvider);

        assertNotNull(dto);
        assertEquals("http://example.com/image.jpg", dto.getImageUrl());
    }
}
