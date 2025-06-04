package app.visualmusic.cover.port.input.usecase.reference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import app.visualmusic.cover.port.input.mapper.ReferenceMapper;
import app.visualmusic.cover.port.output.ReferenceOutputPort;
import app.visualmusic.cover.shared.dto.ReferenceItemResponse;
import app.visualmusic.cover.domain.ReferenceItem;
import app.visualmusic.cover.domain.Style;  // Импортируй твой класс Style, если есть

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class GetAllStylesUseCaseTest {

    private ReferenceOutputPort referenceOutputPort;
    private ReferenceMapper mapper;
    private GetAllStylesUseCase getAllStylesUseCase;

    @BeforeEach
    void setUp() {
        referenceOutputPort = mock(ReferenceOutputPort.class);
        mapper = mock(ReferenceMapper.class);
        getAllStylesUseCase = new GetAllStylesUseCase(referenceOutputPort, mapper);
    }

    @Test
    void invoke_shouldReturnMappedList() {
        Style style1 = mock(Style.class);
        Style style2 = mock(Style.class);

        ReferenceItemResponse response1 = mock(ReferenceItemResponse.class);
        ReferenceItemResponse response2 = mock(ReferenceItemResponse.class);

        when(referenceOutputPort.getAllStyles()).thenReturn(List.of(style1, style2));
        when(mapper.toItem(style1)).thenReturn(response1);
        when(mapper.toItem(style2)).thenReturn(response2);

        List<ReferenceItemResponse> result = getAllStylesUseCase.invoke();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(response1, result.get(0));
        assertEquals(response2, result.get(1));

        verify(referenceOutputPort).getAllStyles();
        verify(mapper).toItem(style1);
        verify(mapper).toItem(style2);
    }
}
