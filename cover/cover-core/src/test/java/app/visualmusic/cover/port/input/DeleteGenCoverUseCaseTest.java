package app.visualmusic.cover.port.input.usecase.cover;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import app.visualmusic.cover.domain.Cover;
import app.visualmusic.cover.port.output.CoverImageOutputPort;
import app.visualmusic.cover.port.output.CoverOutputPort;
import app.visualmusic.cover.port.output.GroupOutputPort;
import app.visualmusic.cover.shared.exception.CoverNotFoundException;
import app.visualmusic.cover.shared.exception.GroupNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class DeleteGenCoverUseCaseTest {

    private GroupOutputPort groupOutputPort;
    private CoverOutputPort coverOutputPort;
    private CoverImageOutputPort coverImageOutputPort;
    private DeleteGenCoverUseCase deleteGenCoverUseCase;

    @BeforeEach
    void setUp() {
        groupOutputPort = mock(GroupOutputPort.class);
        coverOutputPort = mock(CoverOutputPort.class);
        coverImageOutputPort = mock(CoverImageOutputPort.class);
        deleteGenCoverUseCase = new DeleteGenCoverUseCase(groupOutputPort, coverOutputPort, coverImageOutputPort);
    }

    @Test
    void invoke_shouldDeleteCover_whenGroupAndCoverExist() {
        long userId = 1L;
        long groupId = 2L;
        long coverId = 3L;

        Cover cover = mock(Cover.class);

        when(groupOutputPort.existsGeneratedById(userId, groupId)).thenReturn(true);
        when(coverOutputPort.findGeneratedById(userId, groupId, coverId)).thenReturn(Optional.of(cover));

        deleteGenCoverUseCase.invoke(userId, groupId, coverId);

        verify(groupOutputPort).existsGeneratedById(userId, groupId);
        verify(coverOutputPort).findGeneratedById(userId, groupId, coverId);
        verify(coverOutputPort).deleteById(coverId);
        verify(coverImageOutputPort).delete(cover);
    }

    @Test
    void invoke_shouldThrowGroupNotFoundException_whenGroupDoesNotExist() {
        long userId = 1L;
        long groupId = 2L;
        long coverId = 3L;

        when(groupOutputPort.existsGeneratedById(userId, groupId)).thenReturn(false);

        assertThrows(GroupNotFoundException.class, () -> deleteGenCoverUseCase.invoke(userId, groupId, coverId));

        verify(groupOutputPort).existsGeneratedById(userId, groupId);
        verifyNoMoreInteractions(coverOutputPort, coverImageOutputPort);
    }

    @Test
    void invoke_shouldThrowCoverNotFoundException_whenCoverDoesNotExist() {
        long userId = 1L;
        long groupId = 2L;
        long coverId = 3L;

        when(groupOutputPort.existsGeneratedById(userId, groupId)).thenReturn(true);
        when(coverOutputPort.findGeneratedById(userId, groupId, coverId)).thenReturn(Optional.empty());

        assertThrows(CoverNotFoundException.class, () -> deleteGenCoverUseCase.invoke(userId, groupId, coverId));

        verify(groupOutputPort).existsGeneratedById(userId, groupId);
        verify(coverOutputPort).findGeneratedById(userId, groupId, coverId);
        verifyNoMoreInteractions(coverImageOutputPort);
    }
}
