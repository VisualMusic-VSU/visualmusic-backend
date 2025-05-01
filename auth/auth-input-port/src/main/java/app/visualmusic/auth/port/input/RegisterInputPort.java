package app.visualmusic.auth.port.input;

import app.visualmusic.auth.shared.RegisterRequest;
import app.visualmusic.auth.shared.TokensResponse;

public interface RegisterInputPort {
    void invoke(RegisterRequest request);
}
