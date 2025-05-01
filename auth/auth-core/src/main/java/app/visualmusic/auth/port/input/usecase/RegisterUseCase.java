package app.visualmusic.auth.port.input.usecase;

import app.visualmusic.auth.domain.Role;
import app.visualmusic.auth.domain.User;
import app.visualmusic.auth.port.input.RegisterInputPort;
import app.visualmusic.auth.port.input.exception.RoleNotFoundException;
import app.visualmusic.auth.port.input.exception.UserAlreadyExistsException;
import app.visualmusic.auth.port.input.usecase.mapper.UserMapper;
import app.visualmusic.auth.port.output.data.RoleOutputPort;
import app.visualmusic.auth.port.output.data.UserOutputPort;
import app.visualmusic.auth.port.output.security.PasswordEncoder;
import app.visualmusic.auth.shared.RegisterRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterUseCase implements RegisterInputPort {
    private final UserOutputPort userOutputPort;
    private final RoleOutputPort roleOutputPort;
    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    @Override
    public void invoke(RegisterRequest request) {
        String email = request.getEmail();

        if (userOutputPort.exists(email)) {
            throw new UserAlreadyExistsException(email);
        }

        Role role = roleOutputPort.find("ROLE_USER")
                .orElseThrow(() -> new RoleNotFoundException("ROLE_USER"));

        User user = userMapper.toDomain(request)
                .toBuilder()
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        userOutputPort.save(user);
    }
}
