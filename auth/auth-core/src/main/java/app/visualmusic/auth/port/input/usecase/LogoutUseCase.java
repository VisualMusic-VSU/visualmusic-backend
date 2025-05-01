package app.visualmusic.auth.port.input.usecase;

import app.visualmusic.auth.port.input.LogoutInputPort;
import app.visualmusic.auth.port.output.data.RefreshTokenOutputPort;
import app.visualmusic.auth.shared.LogoutRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LogoutUseCase implements LogoutInputPort {
    private final RefreshTokenOutputPort refreshTokenOutputPort;

    @Override
    public void invoke(LogoutRequest request) {
        refreshTokenOutputPort.deleteByToken(request.getRefreshToken());
    }
}
