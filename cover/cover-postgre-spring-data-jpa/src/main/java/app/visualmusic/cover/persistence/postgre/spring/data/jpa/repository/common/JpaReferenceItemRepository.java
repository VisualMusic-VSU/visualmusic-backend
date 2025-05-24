package app.visualmusic.cover.persistence.postgre.spring.data.jpa.repository.common;

import app.visualmusic.cover.persistence.postgre.spring.data.jpa.entity.common.JpaReferenceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface JpaReferenceItemRepository<R extends JpaReferenceItem> extends JpaRepository<R, Long> {
    boolean existsByName(String name);
}
