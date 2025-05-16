package app.visualmusic.cover.persistence.postgre.spring.data.jpa.repository;

import app.visualmusic.cover.persistence.postgre.spring.data.jpa.entity.JpaGenre;

import java.util.Collection;

public interface JpaGenreRepository extends BaseReferenceRepository<JpaGenre> {
    boolean existsAllByNameIn(Collection<String> names);
}
