package app.visualmusic.cover.port.output.spring.data.jpa.mapper;

import app.visualmusic.cover.domain.Group;
import app.visualmusic.cover.persistence.postgre.spring.data.jpa.entity.JpaGroup;
import app.visualmusic.cover.shared.dto.PageResponse;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(
        config = JpaMapperConfig.class,
        uses = JpaReferenceItemMapper.class
)
public interface JpaGroupMapper {
    Group toModel(JpaGroup entity);

    JpaGroup toEntity(Group model);

    default PageResponse<Group> toPage(Page<JpaGroup> entitiesPage) {
        List<Group> groups = entitiesPage.stream()
                .map(this::toModel)
                .toList();

        return PageResponse.<Group>builder()
                .content(groups)
                .page(entitiesPage.getNumber())
                .hasNext(entitiesPage.hasNext())
                .build();
    }
}
