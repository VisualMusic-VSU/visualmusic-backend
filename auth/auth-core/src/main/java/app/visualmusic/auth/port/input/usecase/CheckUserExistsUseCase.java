package app.visualmusic.auth.port.input.usecase;

import app.visualmusic.auth.port.input.CheckUserExistsInputPort;
import app.visualmusic.auth.port.output.data.UserOutputPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CheckUserExistsUseCase implements CheckUserExistsInputPort {
    private final UserOutputPort userOutputPort;

    @Override
    public boolean invoke(long userId) {
        return userOutputPort.exists(userId);
    }
}
