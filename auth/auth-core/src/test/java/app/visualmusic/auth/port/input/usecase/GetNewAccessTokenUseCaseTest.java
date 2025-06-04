package app.visualmusic.auth.port.input.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import app.visualmusic.auth.domain.RefreshToken;
import app.visualmusic.auth.domain.User;
import app.visualmusic.auth.port.input.exception.AuthenticationException;
import app.visualmusic.auth.port.output.data.RefreshTokenOutputPort;
import app.visualmusic.auth.port.output.data.UserOutputPort;
import app.visualmusic.auth.port.output.security.JwtTokenProvider;
import app.visualmusic.auth.shared.AccessTokenResponse;
import app.visualmusic.auth.shared.GetNewAccessTokenRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class GetNewAccessTokenUseCaseTest {

    private RefreshTokenOutputPort refreshTokenOutputPort;
    private UserOutputPort userOutputPort;
    private JwtTokenProvider jwtTokenProvider;

    private GetNewAccessTokenUseCase useCase;

    @BeforeEach
    void setUp() {
        refreshTokenOutputPort = mock(RefreshTokenOutputPort.class);
        userOutputPort = mock(UserOutputPort.class);
        jwtTokenProvider = mock(JwtTokenProvider.class);

        useCase = new GetNewAccessTokenUseCase(refreshTokenOutputPort, userOutputPort, jwtTokenProvider);
    }

    @Test
    void invoke_shouldReturnNewAccessToken_whenRefreshTokenIsValid() {
        String refreshToken = "validRefreshToken";
        Long userId = 42L;
        String deviceId = "device123";
        String newAccessToken = "newAccessToken";

        GetNewAccessTokenRequest request = new GetNewAccessTokenRequest(refreshToken, deviceId);

        when(jwtTokenProvider.isRefreshTokenValid(refreshToken)).thenReturn(true);
        when(jwtTokenProvider.getUserId(refreshToken, false)).thenReturn(userId);

        RefreshToken refreshTokenMock = mock(RefreshToken.class);
        when(refreshTokenMock.getToken()).thenReturn(refreshToken);
        when(refreshTokenOutputPort.find(userId, deviceId)).thenReturn(Optional.of(refreshTokenMock));

        User userMock = mock(User.class);
        when(userOutputPort.findById(userId)).thenReturn(Optional.of(userMock));

        when(jwtTokenProvider.generateAccessToken(userMock)).thenReturn(newAccessToken);

        AccessTokenResponse response = useCase.invoke(request);

        assertNotNull(response);
        assertEquals(newAccessToken, response.getAccessToken());

        verify(jwtTokenProvider).isRefreshTokenValid(refreshToken);
        verify(jwtTokenProvider).getUserId(refreshToken, false);
        verify(refreshTokenOutputPort).find(userId, deviceId);
        verify(userOutputPort).findById(userId);
        verify(jwtTokenProvider).generateAccessToken(userMock);
    }

    @Test
    void invoke_shouldThrow_whenRefreshTokenInvalid() {
        String refreshToken = "invalidToken";
        GetNewAccessTokenRequest request = new GetNewAccessTokenRequest(refreshToken, "device123");

        when(jwtTokenProvider.isRefreshTokenValid(refreshToken)).thenReturn(false);

        AuthenticationException ex = assertThrows(AuthenticationException.class, () -> useCase.invoke(request));

        assertNotNull(ex);

        verify(jwtTokenProvider).isRefreshTokenValid(refreshToken);
        verifyNoMoreInteractions(jwtTokenProvider, refreshTokenOutputPort, userOutputPort);
    }

    @Test
    void invoke_shouldThrow_whenRefreshTokenRevoked() {
        String refreshToken = "someToken";
        Long userId = 42L;
        String deviceId = "device123";

        GetNewAccessTokenRequest request = new GetNewAccessTokenRequest(refreshToken, deviceId);

        when(jwtTokenProvider.isRefreshTokenValid(refreshToken)).thenReturn(true);
        when(jwtTokenProvider.getUserId(refreshToken, false)).thenReturn(userId);

        when(refreshTokenOutputPort.find(userId, deviceId)).thenReturn(Optional.empty());

        AuthenticationException ex = assertThrows(AuthenticationException.class, () -> useCase.invoke(request));
        assertNotNull(ex);

        verify(jwtTokenProvider).isRefreshTokenValid(refreshToken);
        verify(jwtTokenProvider).getUserId(refreshToken, false);
        verify(refreshTokenOutputPort).find(userId, deviceId);
        verifyNoMoreInteractions(userOutputPort);
    }

    @Test
    void invoke_shouldThrow_whenStoredRefreshTokenDoesNotMatch() {
        String refreshToken = "token1";
        Long userId = 42L;
        String deviceId = "device123";

        GetNewAccessTokenRequest request = new GetNewAccessTokenRequest(refreshToken, deviceId);

        when(jwtTokenProvider.isRefreshTokenValid(refreshToken)).thenReturn(true);
        when(jwtTokenProvider.getUserId(refreshToken, false)).thenReturn(userId);

        RefreshToken refreshTokenMock = mock(RefreshToken.class);
        when(refreshTokenMock.getToken()).thenReturn("differentToken");
        when(refreshTokenOutputPort.find(userId, deviceId)).thenReturn(Optional.of(refreshTokenMock));

        AuthenticationException ex = assertThrows(AuthenticationException.class, () -> useCase.invoke(request));
        assertNotNull(ex);

        verify(jwtTokenProvider).isRefreshTokenValid(refreshToken);
        verify(jwtTokenProvider).getUserId(refreshToken, false);
        verify(refreshTokenOutputPort).find(userId, deviceId);
        verifyNoMoreInteractions(userOutputPort);
    }

    @Test
    void invoke_shouldThrow_whenUserNotFound() {
        String refreshToken = "token1";
        Long userId = 42L;
        String deviceId = "device123";

        GetNewAccessTokenRequest request = new GetNewAccessTokenRequest(refreshToken, deviceId);

        when(jwtTokenProvider.isRefreshTokenValid(refreshToken)).thenReturn(true);
        when(jwtTokenProvider.getUserId(refreshToken, false)).thenReturn(userId);

        RefreshToken refreshTokenMock = mock(RefreshToken.class);
        when(refreshTokenMock.getToken()).thenReturn(refreshToken);
        when(refreshTokenOutputPort.find(userId, deviceId)).thenReturn(Optional.of(refreshTokenMock));

        when(userOutputPort.findById(userId)).thenReturn(Optional.empty());

        AuthenticationException ex = assertThrows(AuthenticationException.class, () -> useCase.invoke(request));
        assertNotNull(ex);

        verify(jwtTokenProvider).isRefreshTokenValid(refreshToken);
        verify(jwtTokenProvider).getUserId(refreshToken, false);
        verify(refreshTokenOutputPort).find(userId, deviceId);
        verify(userOutputPort).findById(userId);
    }
}

