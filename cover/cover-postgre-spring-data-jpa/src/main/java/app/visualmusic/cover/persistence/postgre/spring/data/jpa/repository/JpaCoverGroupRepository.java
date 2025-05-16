package app.visualmusic.cover.persistence.postgre.spring.data.jpa.repository;

import app.visualmusic.cover.persistence.postgre.spring.data.jpa.entity.JpaCoverGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCoverGroupRepository extends JpaRepository<JpaCoverGroup, Long> {
}
