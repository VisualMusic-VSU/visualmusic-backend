package app.visualmusic.auth.port.input.usecase.mapper;

import app.visualmusic.auth.domain.User;
import app.visualmusic.auth.shared.RegisterRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Test
    void toDomain_shouldMapFieldsCorrectly() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("test@example.com");
        request.setPassword("password123");


        User user = userMapper.toDomain(request);

        assertNotNull(user);
        assertEquals(request.getEmail(), user.getEmail());

    }
}
