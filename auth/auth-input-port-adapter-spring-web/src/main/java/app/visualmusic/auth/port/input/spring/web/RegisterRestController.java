package app.visualmusic.auth.port.input.spring.web;

import app.visualmusic.auth.port.input.RegisterInputPort;
import app.visualmusic.auth.shared.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static app.visualmusic.auth.port.input.spring.web.common.AuthEndpointConstants.PRODUCES;
import static app.visualmusic.auth.port.input.spring.web.common.AuthEndpointConstants.REGISTER_PATH;

@RestController
@RequiredArgsConstructor
public class RegisterRestController {
    private final RegisterInputPort registerInputPort;

    @PostMapping(value = REGISTER_PATH, produces = PRODUCES)
    public ResponseEntity<Void> register(@RequestBody RegisterRequest request) {
        registerInputPort.invoke(request);

        return ResponseEntity.ok().build();
    }
}
