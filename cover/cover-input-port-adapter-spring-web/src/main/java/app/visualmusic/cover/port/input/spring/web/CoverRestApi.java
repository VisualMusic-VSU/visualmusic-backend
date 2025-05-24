package app.visualmusic.cover.port.input.spring.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(
        value = "/api/v1/groups",
        produces = "application/json"
)
public interface CoverRestApi {

    @DeleteMapping(value = "/generated/{groupId:^\\d+$}/covers/{coverId:^\\d+$}")
    ResponseEntity<Void> deleteGenCover(
            @AuthenticationPrincipal long userId,
            @PathVariable("groupId") long groupId,
            @PathVariable("coverId") long coverId
    );

    @DeleteMapping(value = "/{groupId:^\\d+$}/covers/{coverId:^\\d+$}")
    ResponseEntity<Void> deleteCover(
            @PathVariable("groupId") long groupId,
            @PathVariable("coverId") long coverId
    );
}
