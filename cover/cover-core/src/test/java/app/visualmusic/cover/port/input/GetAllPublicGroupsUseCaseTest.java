package app.visualmusic.cover.port.input.usecase.group.pubIic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import app.visualmusic.cover.domain.Group;
import app.visualmusic.cover.port.input.mapper.GroupMapper;
import app.visualmusic.cover.port.input.util.RequestValidator;
import app.visualmusic.cover.port.output.CoverImageOutputPort;
import app.visualmusic.cover.port.output.GroupOutputPort;
import app.visualmusic.cover.shared.dto.PageResponse;
import app.visualmusic.cover.shared.dto.group.GroupFiltersRequest;
import app.visualmusic.cover.shared.dto.group.GroupItemResponse;
import app.visualmusic.cover.shared.param.GroupSortParams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

class GetAllPublicGroupsUseCaseTest {

    private GroupOutputPort groupOutputPort;
    private CoverImageOutputPort coverImageOutputPort;
    private RequestValidator requestValidator;
    private GroupMapper groupMapper;
    private GetAllPublicGroupsUseCase getAllPublicGroupsUseCase;

    @BeforeEach
    void setUp() {
        groupOutputPort = mock(GroupOutputPort.class);
        coverImageOutputPort = mock(CoverImageOutputPort.class);
        requestValidator = mock(RequestValidator.class);
        groupMapper = mock(GroupMapper.class);

        getAllPublicGroupsUseCase = new GetAllPublicGroupsUseCase(
                groupOutputPort, coverImageOutputPort, requestValidator, groupMapper);
    }

    @Test
    void invoke_shouldValidateFiltersAndReturnMappedPage() {
        Long curUserId = 1L;
        int page = 0;
        int size = 10;
        GroupSortParams sort = null;  // или подставь конкретное значение, если есть
        GroupFiltersRequest filters = new GroupFiltersRequest();

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

        doNothing().when(requestValidator).validateFilters(filters);
        when(groupOutputPort.findAllPublic(curUserId, page, size, sort, filters)).thenReturn(groupsPage);
        when(groupMapper.toItemPage(any(PageResponse.class), any())).thenReturn(mappedPage);

        PageResponse<GroupItemResponse> result = getAllPublicGroupsUseCase.invoke(curUserId, page, size, sort, filters);

        assertNotNull(result);
        assertEquals(page, result.getPage());
        assertFalse(result.isHasNext());

        verify(requestValidator).validateFilters(filters);
        verify(groupOutputPort).findAllPublic(curUserId, page, size, sort, filters);

        verify(groupMapper).toItemPage(any(PageResponse.class), any());
    }
}
