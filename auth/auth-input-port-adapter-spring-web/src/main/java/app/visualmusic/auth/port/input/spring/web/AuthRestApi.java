package app.visualmusic.auth.port.input.spring.web;

import app.visualmusic.auth.shared.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(
        value = "/api/v1/auth",
        produces = "application/json"
)
public interface AuthRestApi {
    @PostMapping(value = "/register")
    ResponseEntity<Void> register(@RequestBody RegisterRequest request);

    @PostMapping(value = "/login")
    ResponseEntity<TokensResponse> login(@RequestBody LoginRequest request);

    @PostMapping(value = "/token")
    ResponseEntity<AccessTokenResponse> getNewAccessToken(@RequestBody GetNewAccessTokenRequest request);

    @PostMapping(value = "/logout")
    ResponseEntity<Void> logout(@RequestBody LogoutRequest request);
}
