package app.visualmusic.cover.port.output.spring.data.jpa;

import app.visualmusic.cover.domain.Genre;
import app.visualmusic.cover.domain.Mood;
import app.visualmusic.cover.domain.Style;
import app.visualmusic.cover.persistence.postgre.spring.data.jpa.repository.JpaGenreRepository;
import app.visualmusic.cover.persistence.postgre.spring.data.jpa.repository.JpaMoodRepository;
import app.visualmusic.cover.persistence.postgre.spring.data.jpa.repository.JpaStyleRepository;
import app.visualmusic.cover.port.output.ReferenceOutputPort;
import app.visualmusic.cover.port.output.spring.data.jpa.mapper.JpaReferenceItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaReferenceRepositoryAdapter implements ReferenceOutputPort {
    private final JpaGenreRepository jpaGenreRepository;
    private final JpaStyleRepository jpaStyleRepository;
    private final JpaMoodRepository jpaMoodRepository;

    private final JpaReferenceItemMapper mapper;

    @Override
    public List<Genre> getAllGenres() {
        return jpaGenreRepository.findAll().stream()
                .map(mapper::toModel)
                .toList();
    }

    @Override
    public List<Style> getAllStyles() {
        return jpaStyleRepository.findAll().stream()
                .map(mapper::toModel)
                .toList();
    }

    @Override
    public List<Mood> getAllMoods() {
        return jpaMoodRepository.findAll().stream()
                .map(mapper::toModel)
                .toList();
    }

    @Override
    public boolean existsMood(long id) {
        return jpaMoodRepository.existsById(id);
    }

    @Override
    public boolean existsStyle(long id) {
        return jpaStyleRepository.existsById(id);
    }

    @Override
    public boolean existsGenres(List<Long> ids) {
        long existingCount = jpaGenreRepository.countByIdIn(ids);
        return ids.size() == existingCount;
    }
}
