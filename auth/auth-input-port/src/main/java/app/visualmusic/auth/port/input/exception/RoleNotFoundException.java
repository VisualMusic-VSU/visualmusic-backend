package app.visualmusic.auth.port.input.exception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String roleName) {
        super("Role with name=%s not found".formatted(roleName));
    }
}
