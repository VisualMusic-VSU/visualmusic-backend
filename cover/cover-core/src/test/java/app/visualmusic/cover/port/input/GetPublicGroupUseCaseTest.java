package app.visualmusic.cover.port.input.usecase.group.pubIic;

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

class GetPublicGroupUseCaseTest {

    private GroupOutputPort groupOutputPort;
    private GroupMapper groupMapper;
    private GetPublicGroupUseCase getPublicGroupUseCase;

    @BeforeEach
    void setUp() {
        groupOutputPort = mock(GroupOutputPort.class);
        groupMapper = mock(GroupMapper.class);
        getPublicGroupUseCase = new GetPublicGroupUseCase(groupOutputPort, groupMapper);
    }

    @Test
    void invoke_shouldReturnGroupDetail_whenGroupExists() {
        long groupId = 1L;
        Group group = mock(Group.class);
        GroupDetailResponse response = mock(GroupDetailResponse.class);

        when(groupOutputPort.findPublicById(groupId)).thenReturn(Optional.of(group));
        when(groupMapper.toDetail(group)).thenReturn(response);

        GroupDetailResponse result = getPublicGroupUseCase.invoke(groupId);

        assertNotNull(result);
        assertEquals(response, result);

        verify(groupOutputPort).findPublicById(groupId);
        verify(groupMapper).toDetail(group);
    }

    @Test
    void invoke_shouldThrowGroupNotFoundException_whenGroupDoesNotExist() {
        long groupId = 1L;

        when(groupOutputPort.findPublicById(groupId)).thenReturn(Optional.empty());

        assertThrows(GroupNotFoundException.class, () -> getPublicGroupUseCase.invoke(groupId));

        verify(groupOutputPort).findPublicById(groupId);
        verifyNoMoreInteractions(groupMapper);
    }
}
