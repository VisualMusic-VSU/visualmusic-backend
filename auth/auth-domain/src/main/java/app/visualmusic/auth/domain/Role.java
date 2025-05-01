package app.visualmusic.auth.domain;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Role {
    @EqualsAndHashCode.Include
    private Long id;

    private String name;
}
