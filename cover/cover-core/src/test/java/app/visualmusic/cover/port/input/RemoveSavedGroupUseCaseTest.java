package app.visualmusic.cover.port.input.usecase.group.saved;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import app.visualmusic.cover.port.output.GroupOutputPort;
import app.visualmusic.cover.shared.exception.GroupNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RemoveSavedGroupUseCaseTest {

    private GroupOutputPort groupOutputPort;
    private RemoveSavedGroupUseCase removeSavedGroupUseCase;

    @BeforeEach
    void setUp() {
        groupOutputPort = mock(GroupOutputPort.class);
        removeSavedGroupUseCase = new RemoveSavedGroupUseCase(groupOutputPort);
    }

    @Test
    void invoke_shouldRemoveSavedGroup_whenGroupExists() {
        long userId = 1L;
        long groupId = 10L;

        when(groupOutputPort.existsSavedById(userId, groupId)).thenReturn(true);

        removeSavedGroupUseCase.invoke(userId, groupId);

        verify(groupOutputPort).existsSavedById(userId, groupId);
        verify(groupOutputPort).removeSavedById(userId, groupId);
    }

    @Test
    void invoke_shouldThrowGroupNotFoundException_whenGroupDoesNotExist() {
        long userId = 1L;
        long groupId = 10L;

        when(groupOutputPort.existsSavedById(userId, groupId)).thenReturn(false);

        assertThrows(GroupNotFoundException.class, () -> removeSavedGroupUseCase.invoke(userId, groupId));

        verify(groupOutputPort).existsSavedById(userId, groupId);
        verifyNoMoreInteractions(groupOutputPort);
    }
}
