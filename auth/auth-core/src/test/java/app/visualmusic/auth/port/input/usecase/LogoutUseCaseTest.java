package app.visualmusic.auth.port.input.usecase;

import static org.mockito.Mockito.*;

import app.visualmusic.auth.port.output.data.RefreshTokenOutputPort;
import app.visualmusic.auth.shared.LogoutRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LogoutUseCaseTest {

    private RefreshTokenOutputPort refreshTokenOutputPort;
    private LogoutUseCase logoutUseCase;

    @BeforeEach
    void setUp() {
        refreshTokenOutputPort = mock(RefreshTokenOutputPort.class);
        logoutUseCase = new LogoutUseCase(refreshTokenOutputPort);
    }

    @Test
    void invoke_shouldCallDeleteByToken() {
        String refreshToken = "some-refresh-token";
        LogoutRequest request = new LogoutRequest(refreshToken);

        logoutUseCase.invoke(request);

        verify(refreshTokenOutputPort, times(1)).deleteByToken(refreshToken);
    }
}
