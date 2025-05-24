package app.visualmusic.auth.port.input;

public interface CheckUserExistsInputPort {
    boolean invoke(long userId);
}
