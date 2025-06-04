package app.visualmusic.cover.port.input.usecase.group.saved;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import app.visualmusic.cover.domain.Group;
import app.visualmusic.cover.port.input.mapper.GroupMapper;
import app.visualmusic.cover.port.output.AuthServiceOutputPort;
import app.visualmusic.cover.port.output.CoverImageOutputPort;
import app.visualmusic.cover.port.output.GroupOutputPort;
import app.visualmusic.cover.shared.dto.PageResponse;
import app.visualmusic.cover.shared.dto.group.GroupFiltersRequest;
import app.visualmusic.cover.shared.dto.group.GroupItemResponse;
import app.visualmusic.cover.shared.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

class GetAllSavedGroupsUseCaseTest {

    private GroupOutputPort groupOutputPort;
    private AuthServiceOutputPort authService;
    private CoverImageOutputPort coverImageOutputPort;
    private GroupMapper groupMapper;

    private GetAllSavedGroupsUseCase getAllSavedGroupsUseCase;

    @BeforeEach
    void setUp() {
        groupOutputPort = mock(GroupOutputPort.class);
        authService = mock(AuthServiceOutputPort.class);
        coverImageOutputPort = mock(CoverImageOutputPort.class);
        groupMapper = mock(GroupMapper.class);

        getAllSavedGroupsUseCase = new GetAllSavedGroupsUseCase(
                groupOutputPort, authService, coverImageOutputPort, groupMapper);
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

        PageResponse<GroupItemResponse> mappedPage = PageResponse.<GroupItemResponse>builder()
                .content(Collections.emptyList())
                .page(page)
                .hasNext(false)
                .build();

        when(groupOutputPort.findAllSaved(userId, page, size)).thenReturn(groupsPage);
        when(groupMapper.toItemPage(any(PageResponse.class), any())).thenReturn(mappedPage);

        PageResponse<GroupItemResponse> result = getAllSavedGroupsUseCase.invoke(userId, page, size);

        assertNotNull(result);
        assertEquals(page, result.getPage());
        assertFalse(result.isHasNext());

        verify(authService).existsUserById(userId);
        verify(groupOutputPort).findAllSaved(userId, page, size);
        verify(groupMapper).toItemPage(eq(groupsPage), any());
    }

    @Test
    void invoke_shouldThrowUserNotFoundException_whenUserDoesNotExist() {
        long userId = 1L;
        int page = 0;
        int size = 10;

        when(authService.existsUserById(userId)).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> getAllSavedGroupsUseCase.invoke(userId, page, size));

        verify(authService).existsUserById(userId);
        verifyNoMoreInteractions(groupOutputPort, coverImageOutputPort, groupMapper);
    }
}
