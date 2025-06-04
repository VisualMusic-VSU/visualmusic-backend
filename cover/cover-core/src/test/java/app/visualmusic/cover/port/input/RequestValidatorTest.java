package app.visualmusic.cover.port.input.util;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import app.visualmusic.cover.port.output.ReferenceOutputPort;
import app.visualmusic.cover.shared.dto.group.GroupFiltersRequest;
import app.visualmusic.cover.shared.exception.ReferenceItemNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class RequestValidatorTest {

    private ReferenceOutputPort referenceOutputPort;
    private RequestValidator requestValidator;

    @BeforeEach
    void setUp() {
        referenceOutputPort = mock(ReferenceOutputPort.class);
        requestValidator = new RequestValidator(referenceOutputPort);
    }

    @Test
    void validateFilters_shouldPassWhenFiltersAreNull() {
        assertDoesNotThrow(() -> requestValidator.validateFilters(null));
    }

    @Test
    void validateFilters_shouldPassWhenAllFiltersValid() {
        GroupFiltersRequest filters = new GroupFiltersRequest();
        filters.setMoodId(1L);
        filters.setStyleId(2L);
        filters.setGenreIds(List.of(3L, 4L));

        when(referenceOutputPort.existsMood(1L)).thenReturn(true);
        when(referenceOutputPort.existsStyle(2L)).thenReturn(true);
        when(referenceOutputPort.existsGenres(List.of(3L, 4L))).thenReturn(true);

        assertDoesNotThrow(() -> requestValidator.validateFilters(filters));

        verify(referenceOutputPort).existsMood(1L);
        verify(referenceOutputPort).existsStyle(2L);
        verify(referenceOutputPort).existsGenres(List.of(3L, 4L));
    }

    @Test
    void validateFilters_shouldThrowWhenMoodNotExists() {
        GroupFiltersRequest filters = new GroupFiltersRequest();
        filters.setMoodId(1L);

        when(referenceOutputPort.existsMood(1L)).thenReturn(false);

        ReferenceItemNotFoundException ex = assertThrows(
                ReferenceItemNotFoundException.class,
                () -> requestValidator.validateFilters(filters)
        );
        assertEquals("Mood not found", ex.getMessage());

        verify(referenceOutputPort).existsMood(1L);
        verifyNoMoreInteractions(referenceOutputPort);
    }

    @Test
    void validateFilters_shouldThrowWhenStyleNotExists() {
        GroupFiltersRequest filters = new GroupFiltersRequest();
        filters.setStyleId(2L);

        when(referenceOutputPort.existsStyle(2L)).thenReturn(false);

        ReferenceItemNotFoundException ex = assertThrows(
                ReferenceItemNotFoundException.class,
                () -> requestValidator.validateFilters(filters)
        );
        assertEquals("Style not found", ex.getMessage());

        verify(referenceOutputPort).existsStyle(2L);
        verifyNoMoreInteractions(referenceOutputPort);
    }

    @Test
    void validateFilters_shouldThrowWhenGenresNotExists() {
        GroupFiltersRequest filters = new GroupFiltersRequest();
        filters.setGenreIds(List.of(3L, 4L));

        when(referenceOutputPort.existsGenres(List.of(3L, 4L))).thenReturn(false);

        ReferenceItemNotFoundException ex = assertThrows(
                ReferenceItemNotFoundException.class,
                () -> requestValidator.validateFilters(filters)
        );
        assertEquals("Some genres were not found", ex.getMessage());

        verify(referenceOutputPort).existsGenres(List.of(3L, 4L));
        verifyNoMoreInteractions(referenceOutputPort);
    }

    @Test
    void validateFilters_shouldPassWhenGenreIdsEmpty() {
        GroupFiltersRequest filters = new GroupFiltersRequest();
        filters.setGenreIds(List.of());


        assertDoesNotThrow(() -> requestValidator.validateFilters(filters));
        verifyNoInteractions(referenceOutputPort);
    }
}
