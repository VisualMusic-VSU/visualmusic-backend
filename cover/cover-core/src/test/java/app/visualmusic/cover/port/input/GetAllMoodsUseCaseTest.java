package app.visualmusic.cover.port.input.usecase.reference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import app.visualmusic.cover.port.input.mapper.ReferenceMapper;
import app.visualmusic.cover.port.output.ReferenceOutputPort;
import app.visualmusic.cover.shared.dto.ReferenceItemResponse;
import app.visualmusic.cover.domain.ReferenceItem;
import app.visualmusic.cover.domain.Mood;  // Добавь импорт твоего класса Mood, если он есть

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class GetAllMoodsUseCaseTest {

    private ReferenceOutputPort referenceOutputPort;
    private ReferenceMapper mapper;
    private GetAllMoodsUseCase getAllMoodsUseCase;

    @BeforeEach
    void setUp() {
        referenceOutputPort = mock(ReferenceOutputPort.class);
        mapper = mock(ReferenceMapper.class);
        getAllMoodsUseCase = new GetAllMoodsUseCase(referenceOutputPort, mapper);
    }

    @Test
    void invoke_shouldReturnMappedList() {
        Mood mood1 = mock(Mood.class);
        Mood mood2 = mock(Mood.class);

        ReferenceItemResponse response1 = mock(ReferenceItemResponse.class);
        ReferenceItemResponse response2 = mock(ReferenceItemResponse.class);

        when(referenceOutputPort.getAllMoods()).thenReturn(List.of(mood1, mood2));
        when(mapper.toItem(mood1)).thenReturn(response1);
        when(mapper.toItem(mood2)).thenReturn(response2);

        List<ReferenceItemResponse> result = getAllMoodsUseCase.invoke();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(response1, result.get(0));
        assertEquals(response2, result.get(1));

        verify(referenceOutputPort).getAllMoods();
        verify(mapper).toItem(mood1);
        verify(mapper).toItem(mood2);
    }
}
