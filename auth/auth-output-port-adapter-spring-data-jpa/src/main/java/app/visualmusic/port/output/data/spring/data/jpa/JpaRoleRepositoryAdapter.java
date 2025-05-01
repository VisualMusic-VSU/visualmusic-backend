package app.visualmusic.port.output.data.spring.data.jpa;

import app.visualmusic.auth.domain.Role;
import app.visualmusic.auth.persistence.postgre.spring.data.jpa.repository.JpaRoleRepository;
import app.visualmusic.auth.port.output.data.RoleOutputPort;
import app.visualmusic.port.output.data.spring.data.jpa.mapper.JpaRoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaRoleRepositoryAdapter implements RoleOutputPort {
    private final JpaRoleRepository jpaRepository;

    private final JpaRoleMapper mapper;

    @Override
    public Optional<Role> find(String name) {
        return jpaRepository.findByName(name)
                .map(mapper::toDomain);
    }
}
