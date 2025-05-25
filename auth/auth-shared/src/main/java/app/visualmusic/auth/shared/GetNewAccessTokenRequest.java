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
@Schema(description = "Запрос на получение нового JWT токена доступа")
public class GetNewAccessTokenRequest {
    @NotBlank
    @Schema(description = "JWT токен обновления", example = "dGhpc0lzQVRlc3RSZWZyZXNoVG9rZW4=")
    private String refreshToken;

    @NotBlank
    @Schema(description = "Идентификатор устройства", example = "device-12345")
    private String deviceId;
}
