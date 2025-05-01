package app.visualmusic.auth.port.input.spring.web.common;

public class AuthEndpointConstants {

    public static final String PRODUCES = "application/json";

    public static final String AUTH_BASE_PATH = "api/v1/auth";

    public static final String LOGIN_PATH = AUTH_BASE_PATH + "/login";
    public static final String LOGOUT_PATH = AUTH_BASE_PATH + "/logout";
    public static final String TOKEN_PATH = AUTH_BASE_PATH + "/token";

    public static final String REGISTER_PATH = AUTH_BASE_PATH + "/register";

    private AuthEndpointConstants() {
        throw new IllegalStateException("Utility class");
    }
}
