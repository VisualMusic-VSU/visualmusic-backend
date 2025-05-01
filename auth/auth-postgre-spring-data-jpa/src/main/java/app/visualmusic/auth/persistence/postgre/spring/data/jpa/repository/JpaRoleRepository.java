package app.visualmusic.auth.persistence.postgre.spring.data.jpa.repository;

import app.visualmusic.auth.persistence.postgre.spring.data.jpa.entity.JpaRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaRoleRepository extends JpaRepository<JpaRole, Long> {
    Optional<JpaRole> findByName(String name);
}