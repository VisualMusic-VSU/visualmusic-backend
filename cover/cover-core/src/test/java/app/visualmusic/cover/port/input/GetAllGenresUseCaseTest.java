package app.visualmusic.cover.port.input.usecase.reference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import app.visualmusic.cover.port.input.mapper.ReferenceMapper;
import app.visualmusic.cover.port.output.ReferenceOutputPort;
import app.visualmusic.cover.shared.dto.ReferenceItemResponse;
import app.visualmusic.cover.domain.ReferenceItem;
import app.visualmusic.cover.domain.Genre;  // <--- импортируй класс Genre
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class GetAllGenresUseCaseTest {

    private ReferenceOutputPort referenceOutputPort;
    private ReferenceMapper mapper;
    private GetAllGenresUseCase getAllGenresUseCase;

    @BeforeEach
    void setUp() {
        referenceOutputPort = mock(ReferenceOutputPort.class);
        mapper = mock(ReferenceMapper.class);
        getAllGenresUseCase = new GetAllGenresUseCase(referenceOutputPort, mapper);
    }

    @Test
    void invoke_shouldReturnMappedList() {
        Genre genre1 = mock(Genre.class);
        Genre genre2 = mock(Genre.class);

        ReferenceItemResponse response1 = mock(ReferenceItemResponse.class);
        ReferenceItemResponse response2 = mock(ReferenceItemResponse.class);

        when(referenceOutputPort.getAllGenres()).thenReturn(List.of(genre1, genre2));
        when(mapper.toItem(genre1)).thenReturn(response1);
        when(mapper.toItem(genre2)).thenReturn(response2);

        List<ReferenceItemResponse> result = getAllGenresUseCase.invoke();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(response1, result.get(0));
        assertEquals(response2, result.get(1));

        verify(referenceOutputPort).getAllGenres();
        verify(mapper).toItem(genre1);
        verify(mapper).toItem(genre2);
    }
}
