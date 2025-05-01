package app.visualmusic.auth.domain;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class RefreshToken {
    @EqualsAndHashCode.Include
    private Long id;

    private User user;

    private String token;

    private String deviceId;

    private Instant expiresAt;
}
