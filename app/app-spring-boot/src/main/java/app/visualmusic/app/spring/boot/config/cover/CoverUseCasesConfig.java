package app.visualmusic.app.spring.boot.config.cover;

import app.visualmusic.cover.port.input.cover.DeleteCoverInputPort;
import app.visualmusic.cover.port.input.cover.DeleteGenCoverInputPort;
import app.visualmusic.cover.port.input.group.DeleteGroupInputPort;
import app.visualmusic.cover.port.input.group.GetAllGroupsInputPort;
import app.visualmusic.cover.port.input.group.generated.DeleteGenGroupInputPort;
import app.visualmusic.cover.port.input.group.generated.GetAllGenGroupsInputPort;
import app.visualmusic.cover.port.input.group.generated.GetGenGroupInputPort;
import app.visualmusic.cover.port.input.group.generated.UpdateAccessGenGroupInputPort;
import app.visualmusic.cover.port.input.group.pubIic.GetAllPublicGroupsInputPort;
import app.visualmusic.cover.port.input.group.pubIic.GetPublicGroupInputPort;
import app.visualmusic.cover.port.input.group.saved.GetAllSavedGroupsInputPort;
import app.visualmusic.cover.port.input.group.saved.GetSavedGroupInputPort;
import app.visualmusic.cover.port.input.group.saved.RemoveSavedGroupInputPort;
import app.visualmusic.cover.port.input.mapper.GroupMapper;
import app.visualmusic.cover.port.input.usecase.cover.DeleteCoverUseCase;
import app.visualmusic.cover.port.input.usecase.cover.DeleteGenCoverUseCase;
import app.visualmusic.cover.port.input.usecase.group.DeleteGroupUseCase;
import app.visualmusic.cover.port.input.usecase.group.GetAllGroupsUseCase;
import app.visualmusic.cover.port.input.usecase.group.generated.DeleteGenGroupUseCase;
import app.visualmusic.cover.port.input.usecase.group.generated.GetAllGenGroupsUseCase;
import app.visualmusic.cover.port.input.usecase.group.generated.GetGenGroupUseCase;
import app.visualmusic.cover.port.input.usecase.group.generated.UpdateAccessGenGroupUseCase;
import app.visualmusic.cover.port.input.usecase.group.pubIic.GetAllPublicGroupsUseCase;
import app.visualmusic.cover.port.input.usecase.group.pubIic.GetPublicGroupUseCase;
import app.visualmusic.cover.port.input.usecase.group.saved.GetAllSavedGroupsUseCase;
import app.visualmusic.cover.port.input.usecase.group.saved.GetSavedGroupUseCase;
import app.visualmusic.cover.port.input.usecase.group.saved.RemoveSavedGroupUseCase;
import app.visualmusic.cover.port.input.usecase.proxy.spring.transactional.*;
import app.visualmusic.cover.port.input.util.RequestValidator;
import app.visualmusic.cover.port.output.AuthServiceOutputPort;
import app.visualmusic.cover.port.output.CoverOutputPort;
import app.visualmusic.cover.port.output.GroupOutputPort;
import app.visualmusic.cover.port.output.ReferenceOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@RequiredArgsConstructor
public class CoverUseCasesConfig {
    private final AuthServiceOutputPort authServiceOutputPort;
    private final CoverOutputPort coverOutputPort;
    private final GroupOutputPort groupOutputPort;
    private final ReferenceOutputPort referenceOutputPort;

    private final GroupMapper groupMapper;

    @Bean
    public RequestValidator requestValidator() {
        return new RequestValidator(referenceOutputPort);
    }

    @Bean
    public DeleteCoverInputPort deleteCoverUseCase() {
        return new DeleteCoverUseCase(coverOutputPort);
    }

    @Bean
    @Primary
    public DeleteCoverInputPort transactionalDeleteCoverUseCase(
            @Qualifier("deleteCoverUseCase") DeleteCoverInputPort delegate
    ) {
        return new TransactionalDeleteCoverInputPort(delegate);
    }

    @Bean
    public DeleteGenCoverInputPort deleteGenCoverUseCase() {
        return new DeleteGenCoverUseCase(groupOutputPort, coverOutputPort);
    }

    @Bean
    @Primary
    public DeleteGenCoverInputPort transactionalDeleteGenCoverUseCase(
            @Qualifier("deleteGenCoverUseCase") DeleteGenCoverInputPort delegate
    ) {
        return new TransactionalDeleteGenCoverInputPort(delegate);
    }

    @Bean
    public DeleteGenGroupInputPort deleteGenGroupUseCase() {
        return new DeleteGenGroupUseCase(groupOutputPort);
    }

    @Bean
    @Primary
    public DeleteGenGroupInputPort transactionalDeleteGenGroupUseCase(
            @Qualifier("deleteGenGroupUseCase") DeleteGenGroupInputPort delegate
    ) {
        return new TransactionalDeleteGenGroupInputPort(delegate);
    }

    @Bean
    public GetAllGenGroupsInputPort getAllGenGroupsUseCase() {
        return new GetAllGenGroupsUseCase(groupOutputPort, authServiceOutputPort, groupMapper);
    }

    @Bean
    public GetGenGroupInputPort getGenGroupUseCase() {
        return new GetGenGroupUseCase(groupOutputPort, groupMapper);
    }

    @Bean
    public UpdateAccessGenGroupInputPort updateAccessGenGroupUseCase() {
        return new UpdateAccessGenGroupUseCase(groupOutputPort, authServiceOutputPort);
    }

    @Bean
    public GetAllPublicGroupsInputPort getAllPublicGroupsUseCase(RequestValidator requestValidator) {
        return new GetAllPublicGroupsUseCase(groupOutputPort, requestValidator, groupMapper);
    }

    @Bean
    public GetPublicGroupInputPort getPublicGroupUseCase() {
        return new GetPublicGroupUseCase(groupOutputPort, groupMapper);
    }

    @Bean
    public GetAllSavedGroupsInputPort getAllSavedGroupsInputPort() {
        return new GetAllSavedGroupsUseCase(groupOutputPort, authServiceOutputPort, groupMapper);
    }

    @Bean
    public GetSavedGroupInputPort getSavedGroupUseCase() {
        return new GetSavedGroupUseCase(groupOutputPort, groupMapper);
    }

    @Bean
    public RemoveSavedGroupInputPort removeSavedGroupInputPort() {
        return new RemoveSavedGroupUseCase(groupOutputPort);
    }

    @Bean
    @Primary
    public RemoveSavedGroupInputPort transactionalRemoveSavedGroupUseCase(
            @Qualifier("removeSavedGroupInputPort") RemoveSavedGroupInputPort delegate
    ) {
        return new TransactionalRemoveSavedGroupInputPort(delegate);
    }

    @Bean
    public DeleteGroupInputPort deleteGroupUseCase() {
        return new DeleteGroupUseCase(groupOutputPort);
    }

    @Bean
    @Primary
    public DeleteGroupInputPort transactionalDeleteGroupUseCase(
            @Qualifier("deleteGroupUseCase") DeleteGroupInputPort delegate
    ) {
        return new TransactionalDeleteGroupInputPort(delegate);
    }

    @Bean
    public GetAllGroupsInputPort getAllGroupsUseCase(RequestValidator requestValidator) {
        return new GetAllGroupsUseCase(groupOutputPort, requestValidator, groupMapper);
    }
}
