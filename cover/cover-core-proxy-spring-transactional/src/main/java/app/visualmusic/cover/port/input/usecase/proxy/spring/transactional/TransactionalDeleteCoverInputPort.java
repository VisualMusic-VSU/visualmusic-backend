package app.visualmusic.cover.port.input.usecase.proxy.spring.transactional;

import app.visualmusic.cover.port.input.cover.DeleteCoverInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class TransactionalDeleteCoverInputPort implements DeleteCoverInputPort {
    private final DeleteCoverInputPort delegate;

    @Override
    @Transactional(transactionManager = "coverTransactionManager")
    public void invoke(long groupId, long coverId) {
        delegate.invoke(groupId, coverId);
    }
}
