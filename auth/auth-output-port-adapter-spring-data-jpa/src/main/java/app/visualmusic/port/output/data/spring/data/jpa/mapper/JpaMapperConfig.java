package app.visualmusic.port.output.data.spring.data.jpa.mapper;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface JpaMapperConfig {
}
