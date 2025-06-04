package app.visualmusic.cover.port.input.usecase.group.generated;

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

class GetGenGroupUseCaseTest {

    private GroupOutputPort groupOutputPort;
    private GroupMapper groupMapper;
    private GetGenGroupUseCase getGenGroupUseCase;

    @BeforeEach
    void setUp() {
        groupOutputPort = mock(GroupOutputPort.class);
        groupMapper = mock(GroupMapper.class);
        getGenGroupUseCase = new GetGenGroupUseCase(groupOutputPort, groupMapper);
    }

    @Test
    void invoke_shouldReturnGroupDetail_whenGroupExists() {
        long userId = 1L;
        long groupId = 2L;

        Group group = mock(Group.class);
        GroupDetailResponse detailResponse = mock(GroupDetailResponse.class);

        when(groupOutputPort.findGeneratedById(userId, groupId)).thenReturn(Optional.of(group));
        when(groupMapper.toDetail(group)).thenReturn(detailResponse);

        GroupDetailResponse result = getGenGroupUseCase.invoke(userId, groupId);

        assertNotNull(result);
        assertEquals(detailResponse, result);

        verify(groupOutputPort).findGeneratedById(userId, groupId);
        verify(groupMapper).toDetail(group);
    }

    @Test
    void invoke_shouldThrowGroupNotFoundException_whenGroupDoesNotExist() {
        long userId = 1L;
        long groupId = 2L;

        when(groupOutputPort.findGeneratedById(userId, groupId)).thenReturn(Optional.empty());

        assertThrows(GroupNotFoundException.class, () -> getGenGroupUseCase.invoke(userId, groupId));

        verify(groupOutputPort).findGeneratedById(userId, groupId);
        verifyNoMoreInteractions(groupMapper);
    }
}
