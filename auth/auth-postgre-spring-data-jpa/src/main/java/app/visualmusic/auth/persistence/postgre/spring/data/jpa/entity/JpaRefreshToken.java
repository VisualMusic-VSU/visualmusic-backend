package app.visualmusic.auth.persistence.postgre.spring.data.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "refresh_tokens")
public class JpaRefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private JpaUser user;

    @Column(name = "token", columnDefinition = "TEXT", nullable = false, unique = true)
    private String token;

    @Column(name = "device_id", columnDefinition = "TEXT", nullable = false, unique = true)
    private String deviceId;

    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;
}
