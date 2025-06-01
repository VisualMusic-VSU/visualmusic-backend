package app.visualmusic.app.spring.boot;

import app.visualmusic.app.spring.boot.config.property.MinioProperties;
import app.visualmusic.security.spring.security.property.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(
        scanBasePackages = "app.visualmusic"
)
@EnableConfigurationProperties(
        value = {JwtProperties.class, MinioProperties.class}
)
public class VisualMusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(VisualMusicApplication.class, args);
    }
}
