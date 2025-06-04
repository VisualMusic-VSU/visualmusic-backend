package app.visualmusic.auth.port.input.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import app.visualmusic.auth.domain.Role;
import app.visualmusic.auth.domain.User;
import app.visualmusic.auth.port.input.exception.RoleNotFoundException;
import app.visualmusic.auth.port.input.exception.UserAlreadyExistsException;
import app.visualmusic.auth.port.output.data.RoleOutputPort;
import app.visualmusic.auth.port.output.data.UserOutputPort;
import app.visualmusic.auth.port.output.security.PasswordEncoder;
import app.visualmusic.auth.port.input.usecase.mapper.UserMapper;
import app.visualmusic.auth.shared.RegisterRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class RegisterUseCaseTest {

    private UserOutputPort userOutputPort;
    private RoleOutputPort roleOutputPort;
    private PasswordEncoder passwordEncoder;
    private UserMapper userMapper;

    private RegisterUseCase registerUseCase;

    @BeforeEach
    void setUp() {
        userOutputPort = mock(UserOutputPort.class);
        roleOutputPort = mock(RoleOutputPort.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userMapper = mock(UserMapper.class);

        registerUseCase = new RegisterUseCase(userOutputPort, roleOutputPort, passwordEncoder, userMapper);
    }

    @Test
    void invoke_shouldSaveUser_whenUserDoesNotExistAndRoleExists() {
        String email = "test@example.com";
        String rawPassword = "password123";
        String encodedPassword = "encodedPassword";

        RegisterRequest request = new RegisterRequest();
        request.setEmail(email);
        request.setPassword(rawPassword);

        when(userOutputPort.exists(email)).thenReturn(false);

        Role role = mock(Role.class);
        when(roleOutputPort.find("ROLE_USER")).thenReturn(Optional.of(role));

        User userFromMapper = mock(User.class);
        User.UserBuilder userBuilderMock = mock(User.UserBuilder.class);

        when(userMapper.toDomain(request)).thenReturn(userFromMapper);
        when(userFromMapper.toBuilder()).thenReturn(userBuilderMock);

        when(userBuilderMock.password(encodedPassword)).thenReturn(userBuilderMock);
        when(userBuilderMock.role(role)).thenReturn(userBuilderMock);

        User userBuilt = mock(User.class);
        when(userBuilderMock.build()).thenReturn(userBuilt);

        when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);

        registerUseCase.invoke(request);

        verify(userOutputPort).exists(email);
        verify(roleOutputPort).find("ROLE_USER");
        verify(userMapper).toDomain(request);
        verify(passwordEncoder).encode(rawPassword);
        verify(userOutputPort).save(userBuilt);
    }

    @Test
    void invoke_shouldThrowUserAlreadyExistsException_whenUserExists() {
        String email = "test@example.com";
        RegisterRequest request = new RegisterRequest();
        request.setEmail(email);

        when(userOutputPort.exists(email)).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () -> registerUseCase.invoke(request));

        verify(userOutputPort).exists(email);
        verifyNoMoreInteractions(roleOutputPort, passwordEncoder, userMapper, userOutputPort);
    }

    @Test
    void invoke_shouldThrowRoleNotFoundException_whenRoleMissing() {
        String email = "test@example.com";
        RegisterRequest request = new RegisterRequest();
        request.setEmail(email);
        request.setPassword("pass");

        when(userOutputPort.exists(email)).thenReturn(false);
        when(roleOutputPort.find("ROLE_USER")).thenReturn(Optional.empty());

        assertThrows(RoleNotFoundException.class, () -> registerUseCase.invoke(request));

        verify(userOutputPort).exists(email);
        verify(roleOutputPort).find("ROLE_USER");
        verifyNoMoreInteractions(passwordEncoder, userMapper, userOutputPort);
    }
}
