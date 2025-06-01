package app.visualmusic.cover.persistence.postgre.spring.data.jpa.repository;

import app.visualmusic.cover.persistence.postgre.spring.data.jpa.entity.JpaCover;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaCoverRepository extends JpaRepository<JpaCover, Long> {
    Optional<JpaCover> findByGroupUserIdAndGroupIdAndId(Long userId, Long groupId, Long coverId);

    List<JpaCover> findAllByGroupUserIdAndGroupId(Long userId, Long groupId);

    Optional<JpaCover> findByGroupIdAndId(Long groupId, Long coverId);

    List<JpaCover> findAllByGroupId(Long groupId);
}
