package app.visualmusic.auth.port.output.security;

import app.visualmusic.auth.domain.RefreshToken;
import app.visualmusic.auth.domain.User;

public interface JwtTokenProvider {
    String generateAccessToken(User user);

    RefreshToken generateRefreshToken(User user, String deviceId);

    boolean isAccessTokenValid(String token);

    boolean isRefreshTokenValid(String token);

    String getUserEmail(String token, boolean isAccessToken);

    String getRole(String accessToken);
}
