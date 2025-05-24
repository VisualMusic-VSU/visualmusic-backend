package app.visualmusic.cover.shared.dto.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class GroupDetailResponse {
    private Long id;

    private String title;

    private Instant createdAt;

    private boolean isPrivate;

    private Set<CoverItemData> covers;

    private Set<String> genres;

    private String mood;

    private String style;

    private String lyrics;

    private String prompt;
}
