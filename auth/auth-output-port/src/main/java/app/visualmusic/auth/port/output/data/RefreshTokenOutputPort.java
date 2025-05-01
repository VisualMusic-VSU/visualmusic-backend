package app.visualmusic.auth.port.output.data;

import app.visualmusic.auth.domain.RefreshToken;

import java.util.Optional;

public interface RefreshTokenOutputPort {
    Optional<RefreshToken> findToken(String userEmail, String deviceId);

    void save(RefreshToken refreshToken);

    void deleteByToken(String token);
}
