package app.visualmusic.cover.port.input.spring.web.controller;

import app.visualmusic.cover.port.input.cover.DeleteCoverInputPort;
import app.visualmusic.cover.port.input.cover.DeleteGenCoverInputPort;
import app.visualmusic.cover.port.input.spring.web.CoverRestApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CoverRestController implements CoverRestApi {
    private final DeleteGenCoverInputPort deleteGenCoverInputPort;
    private final DeleteCoverInputPort deleteCoverInputPort;

    @Override
    public ResponseEntity<Void> deleteGenCover(long userId, long groupId, long coverId) {
        deleteGenCoverInputPort.invoke(userId, groupId, coverId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteCover(long groupId, long coverId) {
        deleteCoverInputPort.invoke(groupId, coverId);
        return ResponseEntity.ok().build();
    }
}
