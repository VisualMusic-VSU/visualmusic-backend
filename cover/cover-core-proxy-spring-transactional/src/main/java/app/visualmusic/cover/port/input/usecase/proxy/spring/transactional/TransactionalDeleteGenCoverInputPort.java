package app.visualmusic.cover.port.input.usecase.proxy.spring.transactional;

import app.visualmusic.cover.port.input.cover.DeleteGenCoverInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class TransactionalDeleteGenCoverInputPort implements DeleteGenCoverInputPort {
    private final DeleteGenCoverInputPort delegate;

    @Override
    @Transactional(transactionManager = "coverTransactionManager")
    public void invoke(long userId, long groupId, long coverId) {
        delegate.invoke(userId, groupId, coverId);
    }
}
