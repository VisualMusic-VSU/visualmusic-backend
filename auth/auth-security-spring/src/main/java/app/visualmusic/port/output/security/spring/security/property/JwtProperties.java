package app.visualmusic.port.output.security.spring.security.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.jwt")
@RequiredArgsConstructor
@Getter
public class JwtProperties {
    private final String accessSecret;

    private final String refreshSecret;

    private final String accessTokenExpiration;

    private final String refreshTokenExpiration;
}
