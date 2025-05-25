package app.visualmusic.auth.shared;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на выход пользователя из системы")
public class LogoutRequest {
    @NotBlank
    @Schema(description = "JWT токен доступа", example = "dGhpc0lzQURlbW9SZWZyZXNo")
    private String refreshToken;
}
