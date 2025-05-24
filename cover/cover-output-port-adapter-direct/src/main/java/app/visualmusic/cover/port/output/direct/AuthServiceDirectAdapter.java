package app.visualmusic.cover.port.output.direct;

import app.visualmusic.auth.port.input.CheckUserExistsInputPort;
import app.visualmusic.cover.port.output.AuthServiceOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthServiceDirectAdapter implements AuthServiceOutputPort {
    private final CheckUserExistsInputPort checkUserExistsInputPort;

    @Override
    public boolean existsUserById(long id) {
        return checkUserExistsInputPort.invoke(id);
    }
}
