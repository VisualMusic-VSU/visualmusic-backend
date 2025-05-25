package app.visualmusic.app.spring.boot.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "VisualMusic API",
                description = "API для взаимодействия с серверной частью приложения VisualMusic",
                version = "1.0.0",
                contact = @Contact(
                        name = "Egor Belykh",
                        email = "eg.belykh@yandex.ru",
                        url = "https://t.me/popipopich"
                )
        )
)
@SecurityScheme(
        name = "JWT",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
@Configuration
public class OpenApiConfig {
}
