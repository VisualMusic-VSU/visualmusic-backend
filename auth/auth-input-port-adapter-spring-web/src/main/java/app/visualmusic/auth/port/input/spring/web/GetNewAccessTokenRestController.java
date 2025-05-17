package app.visualmusic.auth.port.input.spring.web;

import app.visualmusic.auth.port.input.GetNewAccessTokenInputPort;
import app.visualmusic.auth.shared.AccessTokenResponse;
import app.visualmusic.auth.shared.GetNewAccessTokenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetNewAccessTokenRestController {
    private final GetNewAccessTokenInputPort getNewAccessTokenInputPort;

    @PostMapping(value = "/api/v1/auth/token", produces = "application/json")
    public ResponseEntity<AccessTokenResponse> getNewAccessToken(@RequestBody GetNewAccessTokenRequest request) {
        var result = getNewAccessTokenInputPort.invoke(request);

        return ResponseEntity.ok(result);
    }
}
