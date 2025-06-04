package app.visualmusic.cover.port.input.usecase.group;

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

class DeleteGroupUseCaseTest {

    private GroupOutputPort groupOutputPort;
    private CoverOutputPort coverOutputPort;
    private CoverImageOutputPort coverImageOutputPort;

    private DeleteGroupUseCase deleteGroupUseCase;

    @BeforeEach
    void setUp() {
        groupOutputPort = mock(GroupOutputPort.class);
        coverOutputPort = mock(CoverOutputPort.class);
        coverImageOutputPort = mock(CoverImageOutputPort.class);

        deleteGroupUseCase = new DeleteGroupUseCase(groupOutputPort, coverOutputPort, coverImageOutputPort);
    }

    @Test
    void invoke_shouldDeleteGroupAndDeleteAllCovers() {
        long groupId = 1L;

        Cover cover1 = mock(Cover.class);
        Cover cover2 = mock(Cover.class);
        List<Cover> covers = List.of(cover1, cover2);

        when(groupOutputPort.existsById(groupId)).thenReturn(true);
        when(coverOutputPort.findAllByGroupId(groupId)).thenReturn(covers);

        deleteGroupUseCase.invoke(groupId);

        verify(groupOutputPort).existsById(groupId);
        verify(coverOutputPort).findAllByGroupId(groupId);
        verify(groupOutputPort).deleteById(groupId);
        verify(coverImageOutputPort).delete(cover1);
        verify(coverImageOutputPort).delete(cover2);
    }

    @Test
    void invoke_shouldThrowGroupNotFoundException_whenGroupDoesNotExist() {
        long groupId = 1L;

        when(groupOutputPort.existsById(groupId)).thenReturn(false);

        assertThrows(GroupNotFoundException.class, () -> deleteGroupUseCase.invoke(groupId));

        verify(groupOutputPort).existsById(groupId);
        verifyNoMoreInteractions(coverOutputPort, coverImageOutputPort, groupOutputPort);
    }
}
