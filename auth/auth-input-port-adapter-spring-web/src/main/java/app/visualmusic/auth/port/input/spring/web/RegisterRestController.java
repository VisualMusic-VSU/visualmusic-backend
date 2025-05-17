package app.visualmusic.auth.port.input.spring.web;

import app.visualmusic.auth.port.input.RegisterInputPort;
import app.visualmusic.auth.shared.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterRestController {
    private final RegisterInputPort registerInputPort;

    @PostMapping(value = "/api/v1/auth/register", produces = "application/json")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest request) {
        registerInputPort.invoke(request);

        return ResponseEntity.ok().build();
    }
}
