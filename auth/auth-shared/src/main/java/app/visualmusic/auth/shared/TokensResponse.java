package app.visualmusic.auth.shared;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokensResponse {
    private String accessToken;

    private String refreshToken;
}
