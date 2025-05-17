package app.visualmusic.auth.port.input.spring.web;

import app.visualmusic.auth.port.input.LogoutInputPort;
import app.visualmusic.auth.shared.LogoutRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LogoutRestController {
    private final LogoutInputPort logoutInputPort;

    @PostMapping(value = "/api/v1/auth/logout", produces = "application/json")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequest request) {
        logoutInputPort.invoke(request);

        return ResponseEntity.ok().build();
    }
}
