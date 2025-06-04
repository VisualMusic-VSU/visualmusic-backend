package app.visualmusic.cover.port.input.usecase.cover;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import app.visualmusic.cover.domain.Cover;
import app.visualmusic.cover.port.output.CoverImageOutputPort;
import app.visualmusic.cover.port.output.CoverOutputPort;
import app.visualmusic.cover.shared.exception.CoverNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class DeleteCoverUseCaseTest {

    private CoverOutputPort coverOutputPort;
    private CoverImageOutputPort coverImageOutputPort;
    private DeleteCoverUseCase deleteCoverUseCase;

    @BeforeEach
    void setUp() {
        coverOutputPort = mock(CoverOutputPort.class);
        coverImageOutputPort = mock(CoverImageOutputPort.class);
        deleteCoverUseCase = new DeleteCoverUseCase(coverOutputPort, coverImageOutputPort);
    }

    @Test
    void invoke_shouldDeleteCover_whenCoverExists() {
        long groupId = 1L;
        long coverId = 2L;

        Cover cover = mock(Cover.class);

        when(coverOutputPort.findById(groupId, coverId)).thenReturn(Optional.of(cover));

        deleteCoverUseCase.invoke(groupId, coverId);

        verify(coverOutputPort).findById(groupId, coverId);
        verify(coverOutputPort).deleteById(coverId);
        verify(coverImageOutputPort).delete(cover);
    }

    @Test
    void invoke_shouldThrowCoverNotFoundException_whenCoverDoesNotExist() {
        long groupId = 1L;
        long coverId = 2L;

        when(coverOutputPort.findById(groupId, coverId)).thenReturn(Optional.empty());

        assertThrows(CoverNotFoundException.class, () -> deleteCoverUseCase.invoke(groupId, coverId));

        verify(coverOutputPort).findById(groupId, coverId);
        verifyNoMoreInteractions(coverOutputPort, coverImageOutputPort);
    }
}
