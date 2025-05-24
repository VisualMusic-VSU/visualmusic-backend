package app.visualmusic.cover.domain;

import lombok.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Cover {
    @EqualsAndHashCode.Include
    private Long id;

    private String imageUrl;
}
