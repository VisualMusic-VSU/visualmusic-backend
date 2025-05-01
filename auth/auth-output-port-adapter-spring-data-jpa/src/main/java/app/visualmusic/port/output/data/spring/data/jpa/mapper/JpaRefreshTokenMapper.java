package app.visualmusic.port.output.data.spring.data.jpa.mapper;

import app.visualmusic.auth.domain.RefreshToken;
import app.visualmusic.auth.persistence.postgre.spring.data.jpa.entity.JpaRefreshToken;
import org.mapstruct.Mapper;

@Mapper(
        config = JpaMapperConfig.class,
        uses = JpaUserMapper.class
)
public interface JpaRefreshTokenMapper {
    RefreshToken toDomain(JpaRefreshToken entity);

    JpaRefreshToken toEntity(RefreshToken domain);
}
