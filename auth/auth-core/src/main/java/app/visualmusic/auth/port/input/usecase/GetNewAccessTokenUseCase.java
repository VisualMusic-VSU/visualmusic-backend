package app.visualmusic.auth.port.input.usecase;

import app.visualmusic.auth.domain.RefreshToken;
import app.visualmusic.auth.domain.User;
import app.visualmusic.auth.port.input.GetNewAccessTokenInputPort;
import app.visualmusic.auth.port.input.exception.AuthenticationException;
import app.visualmusic.auth.port.input.exception.UserNotFoundException;
import app.visualmusic.auth.port.output.data.RefreshTokenOutputPort;
import app.visualmusic.auth.port.output.data.UserOutputPort;
import app.visualmusic.auth.port.output.security.JwtTokenProvider;
import app.visualmusic.auth.shared.AccessTokenResponse;
import app.visualmusic.auth.shared.GetNewAccessTokenRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetNewAccessTokenUseCase implements GetNewAccessTokenInputPort {
    private final RefreshTokenOutputPort refreshTokenOutputPort;
    private final UserOutputPort userOutputPort;

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public AccessTokenResponse invoke(GetNewAccessTokenRequest request) {
        String refreshToken = request.getRefreshToken();

        if (!jwtTokenProvider.isRefreshTokenValid(refreshToken)) {
            throw AuthenticationException.invalidRefreshToken();
        }

        Long userId = jwtTokenProvider.getUserId(refreshToken, false);
        String deviceId = request.getDeviceId();

        RefreshToken savedRefreshToken = refreshTokenOutputPort.find(userId, deviceId)
                .orElseThrow(AuthenticationException::revokedRefreshToken);

        if (!savedRefreshToken.getToken().equals(refreshToken)) {
            throw AuthenticationException.revokedRefreshToken();
        }

        User user = userOutputPort.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        String newAccessToken = jwtTokenProvider.generateAccessToken(user);

        return AccessTokenResponse.of(newAccessToken);
    }
}
