package app.visualmusic.port.output.data.spring.data.jpa;

import app.visualmusic.auth.domain.RefreshToken;
import app.visualmusic.auth.persistence.postgre.spring.data.jpa.repository.JpaRefreshTokenRepository;
import app.visualmusic.auth.port.output.data.RefreshTokenOutputPort;
import app.visualmusic.port.output.data.spring.data.jpa.mapper.JpaRefreshTokenMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaRefreshTokenRepositoryAdapter implements RefreshTokenOutputPort {
    private final JpaRefreshTokenRepository jpaRepository;

    private final JpaRefreshTokenMapper mapper;

    @Override
    public Optional<RefreshToken> find(Long userId, String deviceId) {
        return jpaRepository.findByUserIdAndDeviceId(userId, deviceId)
                .map(mapper::toDomain);
    }

    @Override
    public void deleteByToken(String token) {
        jpaRepository.removeByToken(token);
    }

    @Override
    public void save(RefreshToken refreshToken) {
        jpaRepository.save(mapper.toEntity(refreshToken));
    }
}
