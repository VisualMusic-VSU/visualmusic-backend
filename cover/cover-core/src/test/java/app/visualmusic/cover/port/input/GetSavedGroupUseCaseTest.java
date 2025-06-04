package app.visualmusic.cover.port.input.usecase.group.saved;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import app.visualmusic.cover.domain.Group;
import app.visualmusic.cover.port.input.mapper.GroupMapper;
import app.visualmusic.cover.port.output.GroupOutputPort;
import app.visualmusic.cover.shared.dto.group.GroupDetailResponse;
import app.visualmusic.cover.shared.exception.GroupNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class GetSavedGroupUseCaseTest {

    private GroupOutputPort groupOutputPort;
    private GroupMapper groupMapper;
    private GetSavedGroupUseCase getSavedGroupUseCase;

    @BeforeEach
    void setUp() {
        groupOutputPort = mock(GroupOutputPort.class);
        groupMapper = mock(GroupMapper.class);
        getSavedGroupUseCase = new GetSavedGroupUseCase(groupOutputPort, groupMapper);
    }

    @Test
    void invoke_shouldReturnGroupDetail_whenGroupExists() {
        long userId = 1L;
        long groupId = 10L;

        Group group = mock(Group.class);
        GroupDetailResponse response = mock(GroupDetailResponse.class);

        when(groupOutputPort.findSavedById(userId, groupId)).thenReturn(Optional.of(group));
        when(groupMapper.toDetail(group)).thenReturn(response);

        GroupDetailResponse result = getSavedGroupUseCase.invoke(userId, groupId);

        assertNotNull(result);
        assertEquals(response, result);

        verify(groupOutputPort).findSavedById(userId, groupId);
        verify(groupMapper).toDetail(group);
    }

    @Test
    void invoke_shouldThrowGroupNotFoundException_whenGroupDoesNotExist() {
        long userId = 1L;
        long groupId = 10L;

        when(groupOutputPort.findSavedById(userId, groupId)).thenReturn(Optional.empty());

        assertThrows(GroupNotFoundException.class, () -> getSavedGroupUseCase.invoke(userId, groupId));

        verify(groupOutputPort).findSavedById(userId, groupId);
        verifyNoMoreInteractions(groupMapper);
    }
}
