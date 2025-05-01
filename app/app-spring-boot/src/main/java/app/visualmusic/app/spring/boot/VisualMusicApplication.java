package app.visualmusic.app.spring.boot;

import app.visualmusic.port.output.security.spring.security.property.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(
        scanBasePackages = "app.visualmusic"
)
@EnableConfigurationProperties(
        value = JwtProperties.class
)
public class VisualMusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(VisualMusicApplication.class, args);
    }
}
