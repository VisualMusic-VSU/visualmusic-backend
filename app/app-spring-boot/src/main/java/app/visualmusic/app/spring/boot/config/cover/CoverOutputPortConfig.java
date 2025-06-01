package app.visualmusic.app.spring.boot.config.cover;

import app.visualmusic.app.spring.boot.config.property.MinioProperties;
import app.visualmusic.cover.port.output.CoverImageOutputPort;
import app.visualmusic.cover.port.output.minio.MinioCoverImageStorageAdapter;
import app.visualmusic.port.output.spring.cache.CachedCoverImagesOutputPortProxy;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@RequiredArgsConstructor
public class CoverOutputPortConfig {
    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    @Bean
    public CoverImageOutputPort minioCoverImageStorageAdapter() {
        return new MinioCoverImageStorageAdapter(minioClient, minioProperties.getPresignedUrlExpiration());
    }

    @Bean
    @Primary
    public CoverImageOutputPort cachedCoverImagesOutputPortProxy(
            @Qualifier("minioCoverImageStorageAdapter") CoverImageOutputPort coverImageOutputPort
    ) {
        return new CachedCoverImagesOutputPortProxy(coverImageOutputPort);
    }
}
