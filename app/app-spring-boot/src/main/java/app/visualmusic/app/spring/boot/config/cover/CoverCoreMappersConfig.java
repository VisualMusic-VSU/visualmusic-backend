package app.visualmusic.app.spring.boot.config.cover;

import app.visualmusic.cover.port.input.mapper.GroupMapper;
import app.visualmusic.cover.port.input.mapper.CoverMapper;
import app.visualmusic.cover.port.input.mapper.ReferenceMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoverCoreMappersConfig {
    @Bean
    public GroupMapper coverGroupMapper() {
        return GroupMapper.INSTANCE;
    }

    @Bean
    public CoverMapper coverMapper() {
        return CoverMapper.INSTANCE;
    }

    @Bean
    public ReferenceMapper referenceMapper() {
        return ReferenceMapper.INSTANCE;
    }
}
