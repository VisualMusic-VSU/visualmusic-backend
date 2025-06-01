package app.visualmusic.port.output.spring.cache;

import app.visualmusic.cover.domain.Cover;
import app.visualmusic.cover.port.output.CoverImageOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

@RequiredArgsConstructor
public class CachedCoverImagesOutputPortProxy implements CoverImageOutputPort {
    private final CoverImageOutputPort delegate;

    @Override
    @Cacheable(value = "presignedUrls", key = "#root.args[0].object")
    public String getUrl(Cover cover) {
        return delegate.getUrl(cover);
    }

    @Override
    @CacheEvict(value = "presignedUrls", key = "#root.args[0].object")
    public void delete(Cover cover) {
        delegate.delete(cover);
    }
}
