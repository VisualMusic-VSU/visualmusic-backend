package app.visualmusic.cover.port.input.usecase.proxy.spring.transactional;

import app.visualmusic.cover.port.input.group.generated.DeleteGenGroupInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class TransactionalDeleteGenGroupInputPort implements DeleteGenGroupInputPort {
    private final DeleteGenGroupInputPort delegate;

    @Override
    @Transactional(transactionManager = "coverTransactionManager")
    public void invoke(long userId, long groupId) {
        delegate.invoke(userId, groupId);
    }
}
