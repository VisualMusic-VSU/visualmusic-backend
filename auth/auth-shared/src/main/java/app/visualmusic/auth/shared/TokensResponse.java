package app.visualmusic.auth.shared;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "JWT токены доступа и обновления")
public class TokensResponse {
    @Schema(description = "JWT токен доступа", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String accessToken;

    @Schema(description = "JWT токен обновления", example = "dGhpc0lzUmVmcmVzaFRva2Vu")
    private String refreshToken;
}
