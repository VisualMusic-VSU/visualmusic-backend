package app.visualmusic.cover.port.input.mapper;

import app.visualmusic.cover.domain.Cover;
import app.visualmusic.cover.domain.Group;
import app.visualmusic.cover.shared.dto.PageResponse;
import app.visualmusic.cover.shared.dto.group.GroupDetailResponse;
import app.visualmusic.cover.shared.dto.group.GroupItemResponse;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.function.Function;

@Mapper(
        config = MapperConfig.class,
        uses = {ReferenceMapper.class, CoverMapper.class}
)
public interface GroupMapper {
    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    default GroupItemResponse toItem(Group model, @Context Function<Cover, String> coverImageUrlProvider) {
        return GroupItemResponse.builder()
                .id(model.getId())
                .title(model.getTitle())
                .covers(CoverMapper.INSTANCE.toListItemData(model.getCovers(), coverImageUrlProvider))
                .build();
    }

    GroupDetailResponse toDetail(Group model);

    default PageResponse<GroupItemResponse> toItemPage(
            PageResponse<Group> modelPage,
            @Context Function<Cover, String> coverImageUrlProvider
    ) {
        List<GroupItemResponse> content = modelPage.getContent().stream()
                .map(group -> this.toItem(group, coverImageUrlProvider))
                .toList();

        return PageResponse.<GroupItemResponse>builder()
                .content(content)
                .page(modelPage.getPage())
                .hasNext(modelPage.isHasNext())
                .build();
    }
}
