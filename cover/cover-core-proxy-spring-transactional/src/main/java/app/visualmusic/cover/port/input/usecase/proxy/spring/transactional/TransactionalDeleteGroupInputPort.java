package app.visualmusic.cover.port.input.usecase.proxy.spring.transactional;

import app.visualmusic.cover.port.input.group.DeleteGroupInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class TransactionalDeleteGroupInputPort implements DeleteGroupInputPort {
    private final DeleteGroupInputPort delegate;

    @Override
    @Transactional(transactionManager = "coverTransactionManager")
    public void invoke(long groupId) {
        delegate.invoke(groupId);
    }
}
