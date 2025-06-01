package app.visualmusic.cover.port.input.mapper;

import app.visualmusic.cover.domain.ReferenceItem;
import app.visualmusic.cover.shared.dto.ReferenceItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapperConfig.class)
public interface ReferenceMapper {
    ReferenceMapper INSTANCE = Mappers.getMapper(ReferenceMapper.class);

    ReferenceItemResponse toItem(ReferenceItem model);

    default String toName(ReferenceItem model) {
        return model == null
                ? null
                : model.getName();
    }
}
