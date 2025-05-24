package app.visualmusic.cover.port.input.mapper;

import app.visualmusic.cover.domain.Cover;
import app.visualmusic.cover.shared.dto.group.CoverItemData;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapperConfig.class)
public interface CoverMapper {
    CoverMapper INSTANCE = Mappers.getMapper(CoverMapper.class);

    CoverItemData toItemData(Cover cover);
}
