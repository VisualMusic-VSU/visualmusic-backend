package app.visualmusic.cover.domain;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Mood implements ReferenceItem {
    @EqualsAndHashCode.Include
    Long id;

    String name;
}
