package app.visualmusic.cover.domain;

import lombok.*;

import java.time.Instant;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoverGroup {
    @EqualsAndHashCode.Include
    private Long id;

    private Long userId;

    private Set<Cover> covers;

    private String title;

    private Instant createdAt;

    private boolean isPrivate;

    private Set<Genre> genres;

    private Mood mood;

    private Style style;

    private String lyrics;

    private String prompt;
}
