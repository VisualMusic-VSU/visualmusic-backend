package app.visualmusic.cover.persistence.postgre.spring.data.jpa.repository;

import app.visualmusic.cover.persistence.postgre.spring.data.jpa.entity.JpaGenre;
import app.visualmusic.cover.persistence.postgre.spring.data.jpa.repository.common.JpaReferenceItemRepository;

import java.util.Collection;

public interface JpaGenreRepository extends JpaReferenceItemRepository<JpaGenre> {
    long countByIdIn(Collection<Long> ids);
}
