package app.visualmusic.auth.port.input.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import app.visualmusic.auth.port.output.data.UserOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CheckUserExistsUseCaseTest {

    private UserOutputPort userOutputPort;
    private CheckUserExistsUseCase checkUserExistsUseCase;

    @BeforeEach
    void setUp() {
        userOutputPort = Mockito.mock(UserOutputPort.class);
        checkUserExistsUseCase = new CheckUserExistsUseCase(userOutputPort);
    }

    @Test
    void invoke_shouldReturnTrue_whenUserExists() {
        long userId = 123L;
        when(userOutputPort.exists(userId)).thenReturn(true);

        boolean result = checkUserExistsUseCase.invoke(userId);

        assertTrue(result);
        verify(userOutputPort, times(1)).exists(userId);
    }

    @Test
    void invoke_shouldReturnFalse_whenUserDoesNotExist() {
        long userId = 456L;
        when(userOutputPort.exists(userId)).thenReturn(false);

        boolean result = checkUserExistsUseCase.invoke(userId);

        assertFalse(result);
        verify(userOutputPort, times(1)).exists(userId);
    }
}
