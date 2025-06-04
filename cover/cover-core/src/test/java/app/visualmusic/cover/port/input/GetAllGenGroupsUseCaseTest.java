package app.visualmusic.cover.port.input.usecase.group.generated;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import app.visualmusic.cover.domain.Group;
import app.visualmusic.cover.port.input.mapper.GroupMapper;
import app.visualmusic.cover.port.output.AuthServiceOutputPort;
import app.visualmusic.cover.port.output.CoverImageOutputPort;
import app.visualmusic.cover.port.output.GroupOutputPort;
import app.visualmusic.cover.shared.dto.PageResponse;
import app.visualmusic.cover.shared.dto.group.GroupItemResponse;
import app.visualmusic.cover.shared.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

class GetAllGenGroupsUseCaseTest {

    private GroupOutputPort groupOutputPort;
    private AuthServiceOutputPort authService;
    private CoverImageOutputPort coverImageOutputPort;
    private GroupMapper groupMapper;

    private GetAllGenGroupsUseCase getAllGenGroupsUseCase;

    @BeforeEach
    void setUp() {
        groupOutputPort = mock(GroupOutputPort.class);
        authService = mock(AuthServiceOutputPort.class);
        coverImageOutputPort = mock(CoverImageOutputPort.class);
        groupMapper = mock(GroupMapper.class);

        getAllGenGroupsUseCase = new GetAllGenGroupsUseCase(groupOutputPort, authService, coverImageOutputPort, groupMapper);
    }

    @Test
    void invoke_shouldReturnMappedPage_whenUserExists() {
        long userId = 1L;
        int page = 0;
        int size = 10;

        when(authService.existsUserById(userId)).thenReturn(true);

        PageResponse<Group> groupsPage = PageResponse.<Group>builder()
                .content(Collections.emptyList())
                .page(page)
                .hasNext(false)
                .build();

        when(groupOutputPort.findAllGenerated(userId, page, size)).thenReturn(groupsPage);

        PageResponse<GroupItemResponse> mappedPage = PageResponse.<GroupItemResponse>builder()
                .content(Collections.emptyList())
                .page(page)
                .hasNext(false)
                .build();

        when(groupMapper.toItemPage(any(PageResponse.class), any())).thenReturn(mappedPage);

        PageResponse<GroupItemResponse> result = getAllGenGroupsUseCase.invoke(userId, page, size);

        assertNotNull(result);
        assertEquals(page, result.getPage());
        assertFalse(result.isHasNext());

        verify(authService).existsUserById(userId);
        verify(groupOutputPort).findAllGenerated(userId, page, size);


        verify(groupMapper).toItemPage(any(PageResponse.class), any());
    }

    @Test
    void invoke_shouldThrowUserNotFoundException_whenUserDoesNotExist() {
        long userId = 1L;
        int page = 0;
        int size = 10;

        when(authService.existsUserById(userId)).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> getAllGenGroupsUseCase.invoke(userId, page, size));

        verify(authService).existsUserById(userId);
        verifyNoMoreInteractions(groupOutputPort, coverImageOutputPort, groupMapper);
    }
}
