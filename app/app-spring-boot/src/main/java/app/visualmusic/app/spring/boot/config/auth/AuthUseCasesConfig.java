package app.visualmusic.app.spring.boot.config.auth;

import app.visualmusic.auth.port.input.GetNewAccessTokenInputPort;
import app.visualmusic.auth.port.input.LoginInputPort;
import app.visualmusic.auth.port.input.LogoutInputPort;
import app.visualmusic.auth.port.input.RegisterInputPort;
import app.visualmusic.auth.port.input.usecase.GetNewAccessTokenUseCase;
import app.visualmusic.auth.port.input.usecase.LoginUseCase;
import app.visualmusic.auth.port.input.usecase.LogoutUseCase;
import app.visualmusic.auth.port.input.usecase.RegisterUseCase;
import app.visualmusic.auth.port.input.usecase.mapper.UserMapper;
import app.visualmusic.auth.port.input.usecase.proxy.spring.transactional.TransactionalLogoutUsecaseProxy;
import app.visualmusic.auth.port.output.data.RefreshTokenOutputPort;
import app.visualmusic.auth.port.output.data.RoleOutputPort;
import app.visualmusic.auth.port.output.data.UserOutputPort;
import app.visualmusic.auth.port.output.security.JwtTokenProvider;
import app.visualmusic.auth.port.output.security.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@RequiredArgsConstructor
public class AuthUseCasesConfig {
    private final UserOutputPort userOutputPort;
    private final RoleOutputPort roleOutputPort;
    private final RefreshTokenOutputPort refreshTokenOutputPort;

    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    @Bean
    public GetNewAccessTokenInputPort getNewAccessTokenUseCase() {
        return new GetNewAccessTokenUseCase(refreshTokenOutputPort, userOutputPort, jwtTokenProvider);
    }

    @Bean
    public LoginInputPort loginUseCase() {
        return new LoginUseCase(userOutputPort, refreshTokenOutputPort, jwtTokenProvider, passwordEncoder);
    }

    @Bean
    public LogoutInputPort logoutUseCase() {
        return new LogoutUseCase(refreshTokenOutputPort);
    }

    @Bean
    @Primary
    public LogoutInputPort transactionalLogoutUseCase(
            @Qualifier("logoutUseCase") LogoutInputPort logoutUseCase
    ) {
        return new TransactionalLogoutUsecaseProxy(logoutUseCase);
    }

    @Bean
    public RegisterInputPort registerUseCase() {
        return new RegisterUseCase(userOutputPort, roleOutputPort, passwordEncoder, userMapper);
    }
}
