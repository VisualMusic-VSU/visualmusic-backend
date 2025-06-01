package app.visualmusic.cover.port.output.spring.data.jpa;

import app.visualmusic.cover.domain.Cover;
import app.visualmusic.cover.persistence.postgre.spring.data.jpa.repository.JpaCoverRepository;
import app.visualmusic.cover.port.output.CoverOutputPort;
import app.visualmusic.cover.port.output.spring.data.jpa.mapper.JpaCoverMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaCoverRepositoryAdapter implements CoverOutputPort {
    private final JpaCoverRepository jpaCoverRepository;

    private final JpaCoverMapper mapper;

    @Override
    public void deleteById(long id) {
        jpaCoverRepository.deleteById(id);
    }

    @Override
    public Optional<Cover> findGeneratedById(long userId, long groupId, long coverId) {
        return jpaCoverRepository.findByGroupUserIdAndGroupIdAndId(userId, groupId, coverId)
                .map(mapper::toModel);
    }

    @Override
    public List<Cover> findAllGeneratedByGroupId(long userId, long groupId) {
        return jpaCoverRepository.findAllByGroupUserIdAndGroupId(userId, groupId)
                .stream()
                .map(mapper::toModel)
                .toList();
    }

    @Override
    public Optional<Cover> findById(long groupId, long coverId) {
        return jpaCoverRepository.findByGroupIdAndId(groupId, coverId)
                .map(mapper::toModel);
    }

    @Override
    public List<Cover> findAllByGroupId(long groupId) {
        return jpaCoverRepository.findAllByGroupId(groupId)
                .stream()
                .map(mapper::toModel)
                .toList();
    }
}
