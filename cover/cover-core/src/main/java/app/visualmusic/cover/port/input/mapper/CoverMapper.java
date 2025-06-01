package app.visualmusic.cover.port.input.mapper;

import app.visualmusic.cover.domain.Cover;
import app.visualmusic.cover.shared.dto.group.CoverItemData;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

@Mapper(config = MapperConfig.class)
public interface CoverMapper {
    CoverMapper INSTANCE = Mappers.getMapper(CoverMapper.class);

    @Mapping(target = "imageUrl", expression = "java( imageUrlProvider.apply(cover) )")
    CoverItemData toItemData(Cover cover, Function<Cover, String> imageUrlProvider);

    default List<CoverItemData> toListItemData(Set<Cover> covers, @Context Function<Cover, String> imageUrlProvider) {
        return covers.stream()
                .map(cover -> toItemData(cover, imageUrlProvider))
                .toList();
    }
}
