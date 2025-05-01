package app.visualmusic.port.output.data.spring.data.jpa.mapper;

import app.visualmusic.auth.domain.User;
import app.visualmusic.auth.persistence.postgre.spring.data.jpa.entity.JpaUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        config = JpaMapperConfig.class,
        uses = JpaRoleMapper.class
)
public interface JpaUserMapper {
    @Mapping(source = "passHash", target = "password")
    User toDomain(JpaUser entity);

    @Mapping(source = "password", target = "passHash")
    JpaUser toEntity(User domain);
}
