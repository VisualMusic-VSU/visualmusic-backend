package app.visualmusic.cover.persistence.postgre.spring.data.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseReferenceRepository<R> extends JpaRepository<R, Long> {
    boolean existsByName(String name);
}
