package app.visualmusic.auth.port.input.usecase.proxy.spring.transactional;

import app.visualmusic.auth.port.input.LogoutInputPort;
import app.visualmusic.auth.shared.LogoutRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class TransactionalLogoutUsecaseProxy implements LogoutInputPort {
    private final LogoutInputPort delegate;

    @Override
    @Transactional(transactionManager = "authTransactionManager")
    public void invoke(LogoutRequest request) {
        delegate.invoke(request);
    }
}
