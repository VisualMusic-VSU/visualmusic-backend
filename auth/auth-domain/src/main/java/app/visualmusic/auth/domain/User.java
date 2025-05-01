package app.visualmusic.auth.domain;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder(toBuilder = true)
public class User {
    @EqualsAndHashCode.Include
    private Long id;

    private String username;

    private String email;

    private String password;

    @Builder.Default
    private Instant createdAt = Instant.now();

    private Role role;
}
