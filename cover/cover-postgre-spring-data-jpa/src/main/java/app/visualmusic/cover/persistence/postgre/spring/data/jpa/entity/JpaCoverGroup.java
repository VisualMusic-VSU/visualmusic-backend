package app.visualmusic.cover.persistence.postgre.spring.data.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cover_groups")
public class JpaCoverGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "title", nullable = false, length = 70)
    private String title;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "is_private", nullable = false)
    private Boolean isPrivate;

    @ManyToOne
    @JoinColumn(name = "mood_id")
    private JpaMood mood;

    @ManyToOne
    @JoinColumn(name = "style_id")
    private JpaStyle style;

    @Column(name = "lyrics")
    private String lyrics;

    @Column(name = "prompt")
    private String prompt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cover_groups_genres",
            joinColumns = @JoinColumn(name = "cover_group_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<JpaGenre> genres = new HashSet<>();
}
