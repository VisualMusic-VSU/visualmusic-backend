package app.visualmusic.auth.port.input;

import app.visualmusic.auth.shared.LoginRequest;
import app.visualmusic.auth.shared.TokensResponse;

public interface LoginInputPort {
    TokensResponse invoke(LoginRequest request);
}
