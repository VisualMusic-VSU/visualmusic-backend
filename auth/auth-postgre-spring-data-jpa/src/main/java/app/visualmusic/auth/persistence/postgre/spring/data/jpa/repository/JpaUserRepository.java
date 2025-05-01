package app.visualmusic.auth.persistence.postgre.spring.data.jpa.repository;

import app.visualmusic.auth.persistence.postgre.spring.data.jpa.entity.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<JpaUser, Long> {
    Optional<JpaUser> findByEmail(String email);

    boolean existsByEmail(String email);
}