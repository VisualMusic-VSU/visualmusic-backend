package app.visualmusic.cover.port.input.mapper;

import app.visualmusic.cover.domain.ReferenceItem;
import app.visualmusic.cover.shared.dto.ReferenceItemResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReferenceMapperTest {

    private final ReferenceMapper mapper = ReferenceMapper.INSTANCE;

    @Test
    void toItem_shouldMapFieldsCorrectly() {
        ReferenceItem model = mock(ReferenceItem.class);
        when(model.getId()).thenReturn(10L);
        when(model.getName()).thenReturn("Test Name");

        ReferenceItemResponse response = mapper.toItem(model);

        assertNotNull(response);
        assertEquals(10L, response.getId());
        assertEquals("Test Name", response.getName());
    }

    @Test
    void toName_shouldReturnNameOrNull() {
        ReferenceItem model = mock(ReferenceItem.class);
        when(model.getName()).thenReturn("Sample Name");

        assertEquals("Sample Name", mapper.toName(model));
        assertNull(mapper.toName(null));
    }
}
