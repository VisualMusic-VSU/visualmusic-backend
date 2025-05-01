package app.visualmusic.auth.port.input.usecase.mapper;

import app.visualmusic.auth.domain.User;
import app.visualmusic.auth.shared.RegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
        config = MapperConfig.class
)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toDomain(RegisterRequest dto);
}
