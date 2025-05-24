package app.visualmusic.cover.persistence.postgre.spring.data.jpa.repository;

import app.visualmusic.cover.persistence.postgre.spring.data.jpa.entity.JpaCover;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCoverRepository extends JpaRepository<JpaCover, Long> {
    boolean existsByGroupUserIdAndGroupIdAndId(Long userId, Long groupId, Long coverId);

    boolean existsByGroupIdAndId(Long userId, Long groupId);
}
