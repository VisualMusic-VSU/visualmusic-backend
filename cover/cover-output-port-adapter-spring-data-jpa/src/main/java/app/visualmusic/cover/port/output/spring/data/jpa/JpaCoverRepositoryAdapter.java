package app.visualmusic.cover.port.output.spring.data.jpa;

import app.visualmusic.cover.persistence.postgre.spring.data.jpa.repository.JpaCoverRepository;
import app.visualmusic.cover.port.output.CoverOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JpaCoverRepositoryAdapter implements CoverOutputPort {
    private final JpaCoverRepository jpaCoverRepository;

    @Override
    public void deleteById(long id) {
        jpaCoverRepository.deleteById(id);
    }

    @Override
    public boolean existsGeneratedById(long userId, long groupId, long coverId) {
        return jpaCoverRepository.existsByGroupUserIdAndGroupIdAndId(userId, groupId, coverId);
    }

    @Override
    public boolean existsById(long groupId, long coverId) {
        return jpaCoverRepository.existsByGroupIdAndId(groupId, coverId);
    }
}
