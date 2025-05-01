package app.visualmusic.auth.port.input.usecase;

import app.visualmusic.auth.domain.RefreshToken;
import app.visualmusic.auth.domain.User;
import app.visualmusic.auth.port.input.LoginInputPort;
import app.visualmusic.auth.port.input.exception.UserNotFoundException;
import app.visualmusic.auth.port.input.exception.WrongPasswordException;
import app.visualmusic.auth.port.output.data.RefreshTokenOutputPort;
import app.visualmusic.auth.port.output.data.UserOutputPort;
import app.visualmusic.auth.port.output.security.JwtTokenProvider;
import app.visualmusic.auth.port.output.security.PasswordEncoder;
import app.visualmusic.auth.shared.LoginRequest;
import app.visualmusic.auth.shared.TokensResponse;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class LoginUseCase implements LoginInputPort {
    private final UserOutputPort userOutputPort;
    private final RefreshTokenOutputPort refreshTokenOutputPort;
    private final JwtTokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokensResponse invoke(LoginRequest request) {
        String email = request.getEmail();

        User user = userOutputPort.find(email)
                .orElseThrow(() -> new UserNotFoundException(email));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new WrongPasswordException(email);
        }

        RefreshToken newRefreshToken = tokenProvider.generateRefreshToken(user, request.getDeviceId());
        String newAccessToken = tokenProvider.generateAccessToken(user);

        updateSavedRefreshToken(newRefreshToken);

        return TokensResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken.getToken())
                .build();
    }

    private void updateSavedRefreshToken(RefreshToken newRefreshToken) {
        String userEmail = newRefreshToken.getUser().getEmail();
        String deviceId = newRefreshToken.getDeviceId();

        Optional<RefreshToken> savedRefreshToken = refreshTokenOutputPort.findToken(userEmail, deviceId);

        savedRefreshToken.ifPresent(saved -> newRefreshToken.setId(saved.getId()));

        refreshTokenOutputPort.save(newRefreshToken);
    }
}
