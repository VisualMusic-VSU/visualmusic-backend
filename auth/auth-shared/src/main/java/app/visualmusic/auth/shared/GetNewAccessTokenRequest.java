package app.visualmusic.auth.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetNewAccessTokenRequest {
    private String refreshToken;

    private String deviceId;
}
