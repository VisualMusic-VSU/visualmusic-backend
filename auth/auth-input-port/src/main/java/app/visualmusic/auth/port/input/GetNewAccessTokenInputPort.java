package app.visualmusic.auth.port.input;

import app.visualmusic.auth.shared.AccessTokenResponse;
import app.visualmusic.auth.shared.GetNewAccessTokenRequest;

public interface GetNewAccessTokenInputPort {
    AccessTokenResponse invoke(GetNewAccessTokenRequest request);
}
