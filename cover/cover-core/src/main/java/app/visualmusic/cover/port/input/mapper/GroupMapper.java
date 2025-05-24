package app.visualmusic.cover.port.input.mapper;

import app.visualmusic.cover.domain.Group;
import app.visualmusic.cover.shared.dto.PageResponse;
import app.visualmusic.cover.shared.dto.group.GroupDetailResponse;
import app.visualmusic.cover.shared.dto.group.GroupItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        config = MapperConfig.class,
        uses = {ReferenceMapper.class, CoverMapper.class}
)
public interface GroupMapper {
    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    GroupItemResponse toItem(Group model);

    GroupDetailResponse toDetail(Group model);

    default PageResponse<GroupItemResponse> toItemPage(PageResponse<Group> modelPage) {
        List<GroupItemResponse> content = modelPage.getContent().stream()
                .map(this::toItem)
                .toList();

        return PageResponse.<GroupItemResponse>builder()
                .content(content)
                .page(modelPage.getPage())
                .hasNext(modelPage.isHasNext())
                .build();
    }
}
