package app.visualmusic.auth.port.input.exception;

public class AuthenticationException extends RuntimeException {

    private AuthenticationException(String message) {
        super(message);
    }

    public static AuthenticationException invalidRefreshToken() {
        return new AuthenticationException("Refresh token is invalid");
    }

    public static AuthenticationException revokedRefreshToken() {
        return new AuthenticationException("Refresh token has been revoked");
    }
}
