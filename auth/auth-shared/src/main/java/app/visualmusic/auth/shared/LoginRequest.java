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
@Schema(description = "Запрос на вход пользователя в систему")
public class LoginRequest {
    @Email
    @NotBlank
    @Size(max = 64)
    @Schema(description = "Email пользователя", example = "user@example.com")
    private String email;

    @NotBlank
    @Size(max = 32)
    @Schema(description = "Пароль пользователя", example = "MyS3cr3tP@ssw0rd")
    private String password;

    @NotBlank
    @Schema(description = "Идентификатор устройства", example = "device-abc-123")
    private String deviceId;
}
