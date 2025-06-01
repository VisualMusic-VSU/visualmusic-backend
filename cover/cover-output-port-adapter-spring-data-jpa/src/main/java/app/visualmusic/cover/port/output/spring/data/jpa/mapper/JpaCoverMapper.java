package app.visualmusic.cover.port.output.spring.data.jpa.mapper;

import app.visualmusic.cover.domain.Cover;
import app.visualmusic.cover.persistence.postgre.spring.data.jpa.entity.JpaCover;
import org.mapstruct.Mapper;

@Mapper(
        config = JpaMapperConfig.class
)
public interface JpaCoverMapper {
    Cover toModel(JpaCover entity);
}
