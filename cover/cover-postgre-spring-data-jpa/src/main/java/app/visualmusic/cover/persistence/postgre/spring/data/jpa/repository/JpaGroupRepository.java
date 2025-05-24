package app.visualmusic.cover.persistence.postgre.spring.data.jpa.repository;

import app.visualmusic.cover.persistence.postgre.spring.data.jpa.entity.JpaGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface JpaGroupRepository extends JpaRepository<JpaGroup, Long>,
        JpaSpecificationExecutor<JpaGroup> {
    Page<JpaGroup> findAllByUserId(long userId, Pageable pageable);

    Optional<JpaGroup> findByIdAndUserId(Long id, Long userId);

    Optional<JpaGroup> findByIdAndIsPrivateFalse(Long id);

    boolean existsByIdAndUserId(Long id, Long userId);
}
