package app.visualmusic.auth.port.input.spring.web;

import app.visualmusic.auth.port.input.GetNewAccessTokenInputPort;
import app.visualmusic.auth.port.input.LoginInputPort;
import app.visualmusic.auth.port.input.LogoutInputPort;
import app.visualmusic.auth.port.input.RegisterInputPort;
import app.visualmusic.auth.shared.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthRestController implements AuthRestApi {
    private final RegisterInputPort registerInputPort;
    private final LoginInputPort loginInputPort;
    private final GetNewAccessTokenInputPort getNewAccessTokenInputPort;
    private final LogoutInputPort logoutInputPort;

    @Override
    public ResponseEntity<Void> register(RegisterRequest request) {
        registerInputPort.invoke(request);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<TokensResponse> login(LoginRequest request) {
        var result = loginInputPort.invoke(request);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<AccessTokenResponse> getNewAccessToken(GetNewAccessTokenRequest request) {
        var result = getNewAccessTokenInputPort.invoke(request);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> logout(LogoutRequest request) {
        logoutInputPort.invoke(request);
        return ResponseEntity.ok().build();
    }
}
