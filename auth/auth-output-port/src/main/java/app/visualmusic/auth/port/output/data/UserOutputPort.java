package app.visualmusic.auth.port.output.data;

import app.visualmusic.auth.domain.User;

import java.util.Optional;

public interface UserOutputPort {
    void save(User user);

    Optional<User> find(String email);

    boolean exists(String email);
}
