package app.visualmusic.cover.port.input.usecase.group.generated;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import app.visualmusic.cover.domain.Cover;
import app.visualmusic.cover.port.output.CoverImageOutputPort;
import app.visualmusic.cover.port.output.CoverOutputPort;
import app.visualmusic.cover.port.output.GroupOutputPort;
import app.visualmusic.cover.shared.exception.GroupNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class DeleteGenGroupUseCaseTest {

    private GroupOutputPort groupOutputPort;
    private CoverOutputPort coverOutputPort;
    private CoverImageOutputPort coverImageOutputPort;
    private DeleteGenGroupUseCase deleteGenGroupUseCase;

    @BeforeEach
    void setUp() {
        groupOutputPort = mock(GroupOutputPort.class);
        coverOutputPort = mock(CoverOutputPort.class);
        coverImageOutputPort = mock(CoverImageOutputPort.class);

        deleteGenGroupUseCase = new DeleteGenGroupUseCase(groupOutputPort, coverOutputPort, coverImageOutputPort);
    }

    @Test
    void invoke_shouldDeleteGroupAndRelatedCovers_whenGroupExists() {
        long userId = 1L;
        long groupId = 2L;

        Cover cover1 = mock(Cover.class);
        Cover cover2 = mock(Cover.class);
        List<Cover> covers = List.of(cover1, cover2);

        when(groupOutputPort.existsGeneratedById(userId, groupId)).thenReturn(true);
        when(coverOutputPort.findAllGeneratedByGroupId(userId, groupId)).thenReturn(covers);

        deleteGenGroupUseCase.invoke(userId, groupId);

        verify(groupOutputPort).existsGeneratedById(userId, groupId);
        verify(coverOutputPort).findAllGeneratedByGroupId(userId, groupId);
        verify(groupOutputPort).deleteById(groupId);
        verify(coverImageOutputPort).delete(cover1);
        verify(coverImageOutputPort).delete(cover2);
    }

    @Test
    void invoke_shouldThrowGroupNotFoundException_whenGroupDoesNotExist() {
        long userId = 1L;
        long groupId = 2L;

        when(groupOutputPort.existsGeneratedById(userId, groupId)).thenReturn(false);

        assertThrows(GroupNotFoundException.class, () -> deleteGenGroupUseCase.invoke(userId, groupId));

        verify(groupOutputPort).existsGeneratedById(userId, groupId);
        verifyNoMoreInteractions(coverOutputPort, coverImageOutputPort, groupOutputPort);
    }
}
