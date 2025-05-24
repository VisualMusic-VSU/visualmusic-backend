package app.visualmusic.cover.port.input.usecase.group;


import app.visualmusic.cover.domain.Group;
import app.visualmusic.cover.port.input.group.GetAllGroupsInputPort;
import app.visualmusic.cover.port.input.mapper.GroupMapper;
import app.visualmusic.cover.port.input.util.RequestValidator;
import app.visualmusic.cover.port.output.GroupOutputPort;
import app.visualmusic.cover.shared.dto.PageResponse;
import app.visualmusic.cover.shared.dto.group.GroupFiltersRequest;
import app.visualmusic.cover.shared.dto.group.GroupItemResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetAllGroupsUseCase implements GetAllGroupsInputPort {
    private final GroupOutputPort groupOutputPort;

    private final RequestValidator requestValidator;
    private final GroupMapper groupMapper;

    @Override
    public PageResponse<GroupItemResponse> invoke(
            int page, int size,
            GroupFiltersRequest filters
    ) {
        requestValidator.validateFilters(filters);

        PageResponse<Group> result = groupOutputPort.findAll(page, size, filters);

        return groupMapper.toItemPage(result);
    }
}
