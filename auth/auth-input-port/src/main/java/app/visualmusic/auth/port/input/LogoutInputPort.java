package app.visualmusic.auth.port.input;

import app.visualmusic.auth.shared.LogoutRequest;

public interface LogoutInputPort {
    void invoke(LogoutRequest request);
}
