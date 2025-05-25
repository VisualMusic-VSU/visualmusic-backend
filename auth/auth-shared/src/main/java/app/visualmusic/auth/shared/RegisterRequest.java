package app.visualmusic.auth.shared;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на регистрацию нового пользователя")
public class RegisterRequest {

    @NotBlank
    @Size(min = 6, max = 25)
    @Schema(description = "Логин пользователя", example = "john_doe")
    private String username;

    @Email
    @NotBlank
    @Size(max = 64)
    @Schema(description = "Email пользователя", example = "john.doe@example.com")
    private String email;

    @NotBlank
    @Size(min = 8, max = 32)
    @Schema(description = "Пароль пользователя", example = "StrongP@ssw0rd")
    private String password;
}
