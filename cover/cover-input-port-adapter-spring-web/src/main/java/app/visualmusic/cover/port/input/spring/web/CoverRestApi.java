package app.visualmusic.cover.port.input.spring.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Обложки", description = "Операции для работы с обложками в группе обложек")
@RequestMapping(
        value = "/api/v1/groups",
        produces = "application/json"
)
@Validated
public interface CoverRestApi {

    @Operation(
            summary = "Удалить сгенерированную обложку текущего пользователя",
            description = "Удаляет обложку, сгенерированную текущим пользователем, по ID группы и ID обложки",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Обложка успешно удалена"),
                    @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса"),
                    @ApiResponse(responseCode = "404", description = "Группа обложек или обложка не найдены")
            },
            security = @SecurityRequirement(name = "JWT")
    )
    @DeleteMapping(value = "/generated/{groupId}/covers/{coverId}")
    ResponseEntity<Void> deleteGenCover(
            @AuthenticationPrincipal long userId,
            @PositiveOrZero @PathVariable("groupId") long groupId,
            @PositiveOrZero @PathVariable("coverId") long coverId
    );

    @Operation(
            summary = "Удалить обложку",
            description = "Удаляет обложку по ID группы и ID обложки",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Обложка успешно удалена"),
                    @ApiResponse(responseCode = "404", description = "Группа или обложка не найдены"),
                    @ApiResponse(responseCode = "400", description = "Некорректный ID")
            },
            security = @SecurityRequirement(name = "JWT")
    )
    @DeleteMapping(value = "/{groupId}/covers/{coverId}")
    ResponseEntity<Void> deleteCover(
            @PositiveOrZero @PathVariable("groupId") long groupId,
            @PositiveOrZero @PathVariable("coverId") long coverId
    );
}
