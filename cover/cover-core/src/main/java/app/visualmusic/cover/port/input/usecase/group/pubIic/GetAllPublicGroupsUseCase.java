package app.visualmusic.cover.port.input.usecase.group.pubIic;

import app.visualmusic.cover.domain.Group;
import app.visualmusic.cover.port.input.group.pubIic.GetAllPublicGroupsInputPort;
import app.visualmusic.cover.port.input.mapper.GroupMapper;
import app.visualmusic.cover.port.input.util.RequestValidator;
import app.visualmusic.cover.port.output.GroupOutputPort;
import app.visualmusic.cover.shared.dto.PageResponse;
import app.visualmusic.cover.shared.dto.group.GroupItemResponse;
import app.visualmusic.cover.shared.dto.group.GroupFiltersRequest;
import app.visualmusic.cover.shared.param.GroupSortParams;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetAllPublicGroupsUseCase implements GetAllPublicGroupsInputPort {
    private final GroupOutputPort groupOutputPort;

    private final RequestValidator requestValidator;
    private final GroupMapper groupMapper;

    @Override
    public PageResponse<GroupItemResponse> invoke(
            Long curUserId,
            int page,
            int size,
            GroupSortParams sort,
            GroupFiltersRequest filters
    ) {
        requestValidator.validateFilters(filters);

        PageResponse<Group> result = groupOutputPort.findAllPublic(curUserId, page, size, sort, filters);

        return groupMapper.toItemPage(result);
    }
}
