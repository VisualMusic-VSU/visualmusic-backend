package app.visualmusic.app.spring.boot.config.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties("app.minio")
public class MinioProperties {
    private final String endpoint;

    private final String accessKey;

    private final String secretKey;

    private final String coverBucket;

    private final Duration presignedUrlExpiration;
}
