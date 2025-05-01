package app.visualmusic.auth.port.output.data;

import app.visualmusic.auth.domain.Role;

import java.util.Optional;

public interface RoleOutputPort {
    Optional<Role> find(String name);
}
