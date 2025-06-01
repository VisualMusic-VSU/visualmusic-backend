package app.visualmusic.app.spring.boot.config;

import app.visualmusic.app.spring.boot.config.property.MinioProperties;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@RequiredArgsConstructor
public class CacheConfig {
    private final MinioProperties minioProperties;

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();

        cacheManager.registerCustomCache(
                "presignedUrls",
                Caffeine.newBuilder()
                        .initialCapacity(100)
                        .maximumSize(1_000)
                        .expireAfterWrite(minioProperties.getPresignedUrlExpiration().minusMinutes(5))
                        .build()
        );

        return cacheManager;
    }
}
