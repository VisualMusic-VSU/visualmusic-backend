package app.visualmusic.app.spring.boot.config.auth;

import app.visualmusic.auth.port.input.usecase.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthCoreMappersConfig {
    @Bean
    public UserMapper userMapper() {
        return UserMapper.INSTANCE;
    }
}
