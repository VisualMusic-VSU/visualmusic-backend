package app.visualmusic.cover.port.output.spring.data.jpa.mapper;

import app.visualmusic.cover.domain.Genre;
import app.visualmusic.cover.domain.Mood;
import app.visualmusic.cover.domain.Style;
import app.visualmusic.cover.persistence.postgre.spring.data.jpa.entity.JpaGenre;
import app.visualmusic.cover.persistence.postgre.spring.data.jpa.entity.JpaMood;
import app.visualmusic.cover.persistence.postgre.spring.data.jpa.entity.JpaStyle;
import org.mapstruct.Mapper;

@Mapper(
        config = JpaMapperConfig.class
)
public interface JpaReferenceItemMapper {
    Genre toModel(JpaGenre entity);

    Style toModel(JpaStyle entity);

    Mood toModel(JpaMood entity);
}
