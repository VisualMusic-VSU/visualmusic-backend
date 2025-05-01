package app.visualmusic.auth.persistence.postgre.spring.data.jpa.repository;

import app.visualmusic.auth.persistence.postgre.spring.data.jpa.entity.JpaRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaRefreshTokenRepository extends JpaRepository<JpaRefreshToken, Long> {
    Optional<JpaRefreshToken> findByUserEmailAndDeviceId(String userEmail, String deviceId);

    void removeByToken(String token);
}
