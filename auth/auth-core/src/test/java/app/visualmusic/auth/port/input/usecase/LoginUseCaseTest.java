package app.visualmusic.auth.port.input.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import app.visualmusic.auth.domain.RefreshToken;
import app.visualmusic.auth.domain.User;
import app.visualmusic.auth.port.input.exception.UserNotFoundException;
import app.visualmusic.auth.port.input.exception.WrongPasswordException;
import app.visualmusic.auth.port.output.data.RefreshTokenOutputPort;
import app.visualmusic.auth.port.output.data.UserOutputPort;
import app.visualmusic.auth.port.output.security.JwtTokenProvider;
import app.visualmusic.auth.port.output.security.PasswordEncoder;
import app.visualmusic.auth.shared.LoginRequest;
import app.visualmusic.auth.shared.TokensResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class LoginUseCaseTest {

    private UserOutputPort userOutputPort;
    private RefreshTokenOutputPort refreshTokenOutputPort;
    private JwtTokenProvider tokenProvider;
    private PasswordEncoder passwordEncoder;

    private LoginUseCase loginUseCase;

    @BeforeEach
    void setUp() {
        userOutputPort = mock(UserOutputPort.class);
        refreshTokenOutputPort = mock(RefreshTokenOutputPort.class);
        tokenProvider = mock(JwtTokenProvider.class);
        passwordEncoder = mock(PasswordEncoder.class);

        loginUseCase = new LoginUseCase(userOutputPort, refreshTokenOutputPort, tokenProvider, passwordEncoder);
    }

    @Test
    void invoke_shouldReturnTokensResponse_whenCredentialsAreCorrect() {
        String email = "user@example.com";
        String rawPassword = "password123";
        String deviceId = "device1";

        LoginRequest request = new LoginRequest(email, rawPassword, deviceId);

        User user = mock(User.class);
        when(user.getPassword()).thenReturn("encodedPassword");
        when(userOutputPort.findByEmail(email)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(rawPassword, "encodedPassword")).thenReturn(true);

        RefreshToken refreshToken = mock(RefreshToken.class);
        when(tokenProvider.generateRefreshToken(user, deviceId)).thenReturn(refreshToken);
        when(tokenProvider.generateAccessToken(user)).thenReturn("accessToken123");

        when(refreshToken.getToken()).thenReturn("refreshToken123");
        when(refreshToken.getUser()).thenReturn(user);
        when(refreshToken.getDeviceId()).thenReturn(deviceId);
        when(refreshToken.getId()).thenReturn(null);


        when(refreshTokenOutputPort.find(anyLong(), eq(deviceId))).thenReturn(Optional.empty());

        doNothing().when(refreshTokenOutputPort).save(refreshToken);

        TokensResponse response = loginUseCase.invoke(request);

        assertNotNull(response);
        assertEquals("accessToken123", response.getAccessToken());
        assertEquals("refreshToken123", response.getRefreshToken());

        verify(userOutputPort).findByEmail(email);
        verify(passwordEncoder).matches(rawPassword, "encodedPassword");
        verify(tokenProvider).generateRefreshToken(user, deviceId);
        verify(tokenProvider).generateAccessToken(user);
        verify(refreshTokenOutputPort).find(anyLong(), eq(deviceId));
        verify(refreshTokenOutputPort).save(refreshToken);
    }

    @Test
    void invoke_shouldThrowUserNotFoundException_whenUserDoesNotExist() {
        String email = "unknown@example.com";
        String rawPassword = "password123";
        String deviceId = "device1";

        LoginRequest request = new LoginRequest(email, rawPassword, deviceId);

        when(userOutputPort.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> loginUseCase.invoke(request));

        verify(userOutputPort).findByEmail(email);
        verifyNoMoreInteractions(passwordEncoder, tokenProvider, refreshTokenOutputPort);
    }

    @Test
    void invoke_shouldThrowWrongPasswordException_whenPasswordDoesNotMatch() {
        String email = "user@example.com";
        String rawPassword = "wrongPassword";
        String deviceId = "device1";

        LoginRequest request = new LoginRequest(email, rawPassword, deviceId);

        User user = mock(User.class);
        when(user.getPassword()).thenReturn("encodedPassword");
        when(userOutputPort.findByEmail(email)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(rawPassword, "encodedPassword")).thenReturn(false);

        assertThrows(WrongPasswordException.class, () -> loginUseCase.invoke(request));

        verify(userOutputPort).findByEmail(email);
        verify(passwordEncoder).matches(rawPassword, "encodedPassword");
        verifyNoMoreInteractions(tokenProvider, refreshTokenOutputPort);
    }
}
