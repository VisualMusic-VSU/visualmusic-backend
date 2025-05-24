package app.visualmusic.cover.port.input.usecase.proxy.spring.transactional;

import app.visualmusic.cover.port.input.group.saved.RemoveSavedGroupInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class TransactionalRemoveSavedGroupInputPort implements RemoveSavedGroupInputPort {
    private final RemoveSavedGroupInputPort delegate;

    @Override
    @Transactional(transactionManager = "coverTransactionManager")
    public void invoke(long userId, long groupId) {
        delegate.invoke(userId, groupId);
    }
}
