package app.visualmusic.auth.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(staticName = "of")
public class AccessTokenResponse {
    private String accessToken;
}
