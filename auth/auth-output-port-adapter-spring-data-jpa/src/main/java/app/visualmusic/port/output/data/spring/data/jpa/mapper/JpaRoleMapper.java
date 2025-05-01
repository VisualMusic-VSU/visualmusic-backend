package app.visualmusic.port.output.data.spring.data.jpa.mapper;

import app.visualmusic.auth.domain.Role;
import app.visualmusic.auth.persistence.postgre.spring.data.jpa.entity.JpaRole;
import org.mapstruct.Mapper;

@Mapper(
        config = JpaMapperConfig.class
)
public interface JpaRoleMapper {
    Role toDomain(JpaRole entity);
}
