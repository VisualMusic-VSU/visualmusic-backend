package app.visualmusic.auth.port.input.spring.web;

import app.visualmusic.auth.port.input.LoginInputPort;
import app.visualmusic.auth.shared.LoginRequest;
import app.visualmusic.auth.shared.TokensResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginRestController {
    private final LoginInputPort loginInputPort;

    @PostMapping(value = "/api/v1/auth/login", produces = "application/json")
    public ResponseEntity<TokensResponse> login(@RequestBody LoginRequest request) {
        var result = loginInputPort.invoke(request);

        return ResponseEntity.ok(result);
    }
}
