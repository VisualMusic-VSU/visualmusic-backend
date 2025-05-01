package app.visualmusic.port.output.data.spring.data.jpa;

import app.visualmusic.auth.domain.User;
import app.visualmusic.auth.persistence.postgre.spring.data.jpa.repository.JpaUserRepository;
import app.visualmusic.auth.port.output.data.UserOutputPort;
import app.visualmusic.port.output.data.spring.data.jpa.mapper.JpaUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaUserRepositoryAdapter implements UserOutputPort {
    private final JpaUserRepository jpaRepository;

    private final JpaUserMapper mapper;

    @Override
    public void save(User user) {
        jpaRepository.save(mapper.toEntity(user));
    }

    @Override
    public Optional<User> find(String email) {
        return jpaRepository.findByEmail(email)
                .map(mapper::toDomain);
    }

    @Override
    public boolean exists(String email) {
        return jpaRepository.existsByEmail(email);
    }
}
