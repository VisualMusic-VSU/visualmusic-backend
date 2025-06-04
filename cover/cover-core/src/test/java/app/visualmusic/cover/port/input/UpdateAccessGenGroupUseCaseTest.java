package app.visualmusic.cover.port.input.usecase.group.generated;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import app.visualmusic.cover.domain.Group;
import app.visualmusic.cover.port.output.AuthServiceOutputPort;
import app.visualmusic.cover.port.output.GroupOutputPort;
import app.visualmusic.cover.shared.exception.GroupNotFoundException;
import app.visualmusic.cover.shared.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class UpdateAccessGenGroupUseCaseTest {

    private GroupOutputPort groupOutputPort;
    private AuthServiceOutputPort authService;
    private UpdateAccessGenGroupUseCase updateAccessGenGroupUseCase;

    @BeforeEach
    void setUp() {
        groupOutputPort = mock(GroupOutputPort.class);
        authService = mock(AuthServiceOutputPort.class);
        updateAccessGenGroupUseCase = new UpdateAccessGenGroupUseCase(groupOutputPort, authService);
    }

    @Test
    void invoke_shouldUpdateGroupPrivacy_whenUserAndGroupExist() {
        long userId = 1L;
        long groupId = 2L;
        boolean isPrivate = true;

        Group group = mock(Group.class);

        when(authService.existsUserById(userId)).thenReturn(true);
        when(groupOutputPort.findGeneratedById(userId, groupId)).thenReturn(Optional.of(group));

        updateAccessGenGroupUseCase.invoke(userId, groupId, isPrivate);

        verify(authService).existsUserById(userId);
        verify(groupOutputPort).findGeneratedById(userId, groupId);
        verify(group).setPrivate(isPrivate);
        verify(groupOutputPort).save(group);
    }

    @Test
    void invoke_shouldThrowUserNotFoundException_whenUserDoesNotExist() {
        long userId = 1L;
        long groupId = 2L;
        boolean isPrivate = true;

        when(authService.existsUserById(userId)).thenReturn(false);

        assertThrows(UserNotFoundException.class,
                () -> updateAccessGenGroupUseCase.invoke(userId, groupId, isPrivate));

        verify(authService).existsUserById(userId);
        verifyNoMoreInteractions(groupOutputPort);
    }

    @Test
    void invoke_shouldThrowGroupNotFoundException_whenGroupDoesNotExist() {
        long userId = 1L;
        long groupId = 2L;
        boolean isPrivate = true;

        when(authService.existsUserById(userId)).thenReturn(true);
        when(groupOutputPort.findGeneratedById(userId, groupId)).thenReturn(Optional.empty());

        assertThrows(GroupNotFoundException.class,
                () -> updateAccessGenGroupUseCase.invoke(userId, groupId, isPrivate));

        verify(authService).existsUserById(userId);
        verify(groupOutputPort).findGeneratedById(userId, groupId);
        verifyNoMoreInteractions(groupOutputPort);
    }
}
