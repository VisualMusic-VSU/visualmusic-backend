package app.visualmusic.cover.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Cover {
    @EqualsAndHashCode.Include
    private Long id;

    private String bucket;

    private String object;
}
