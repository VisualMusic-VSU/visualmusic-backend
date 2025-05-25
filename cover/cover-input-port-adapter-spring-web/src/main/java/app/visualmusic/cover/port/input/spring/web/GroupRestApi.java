package app.visualmusic.cover.port.input.spring.web;

import app.visualmusic.cover.shared.dto.PageResponse;
import app.visualmusic.cover.shared.dto.group.GroupDetailResponse;
import app.visualmusic.cover.shared.dto.group.GroupFiltersRequest;
import app.visualmusic.cover.shared.dto.group.GroupItemResponse;
import app.visualmusic.cover.shared.param.GroupSortParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Группы обложек", description = "Операции для работы с группами обложек")
@RequestMapping(
        value = "/api/v1/groups",
        produces = "application/json"
)
@Validated
public interface GroupRestApi {

    @Operation(
            summary = "Получить страницу со всеми группами обложек",
            description = "Возвращает страницу групп с возможностью фильтрации по параметрам",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Страница с группами"),
                    @ApiResponse(responseCode = "204", description = "Групп нет", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса", content = @Content)
            },
            security = @SecurityRequirement(name = "JWT")
    )
    @PostMapping
    ResponseEntity<PageResponse<GroupItemResponse>> getAllGroups(
            @PositiveOrZero @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @Positive @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestBody(required = false) GroupFiltersRequest filters
    );

    @Operation(
            summary = "Удалить группу по ID",
            description = "Удаляет группу с указанным идентификатором",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Группа успешно удалена"),
                    @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса"),
                    @ApiResponse(responseCode = "404", description = "Группа не найдена")
            },
            security = @SecurityRequirement(name = "JWT")
    )
    @DeleteMapping(value = "/{groupId}")
    ResponseEntity<Void> deleteGroup(@PathVariable("groupId") long groupId);

    @Operation(
            summary = "Получить страницу с публичными группами",
            description = "Возвращает страницу публичных групп, не учитывая публичные сгенерированные группы " +
                          "текущего пользователя, с возможностью сортировки и фильтрации",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Страница с публичными группами"),
                    @ApiResponse(responseCode = "204", description = "Публичных групп нет", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса", content = @Content)
            }
    )
    @PostMapping(value = "/public")
    ResponseEntity<PageResponse<GroupItemResponse>> getAllPublicGroups(
            @AuthenticationPrincipal Long userId,
            @PositiveOrZero @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @Positive @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "POPULAR") GroupSortParams sort,
            @RequestBody(required = false) GroupFiltersRequest filters
    );


    @Operation(
            summary = "Получить публичную группу обложек по ID",
            description = "Возвращает подробную информацию о публичной группе по её ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Детальная информация о публичной группе"),
                    @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Публичная группа не найдена", content = @Content)
            }
    )
    @GetMapping(value = "/public/{groupId}")
    ResponseEntity<GroupDetailResponse> getPublicCoverGroup(
            @PositiveOrZero @PathVariable("groupId") long groupId
    );

    @Operation(
            summary = "Получить страницу сгенерированных групп текущего пользователя",
            description = "Возвращает страницу с сгенерированными группами обложек текущего пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Страница с сгенерированными группами"),
                    @ApiResponse(responseCode = "204", description = "Сгенерированных групп нет", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса", content = @Content)
            },
            security = @SecurityRequirement(name = "JWT")
    )
    @GetMapping(value = "/generated")
    ResponseEntity<PageResponse<GroupItemResponse>> getAllGenGroups(
            @AuthenticationPrincipal long userId,
            @PositiveOrZero @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @Positive @RequestParam(name = "size", required = false, defaultValue = "10") int size
    );

    @Operation(
            summary = "Получить сгенерированную группу пользователя по ID",
            description = "Возвращает подробную информацию о сгенерированной группе обложек текущего пользователь по её ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Детальная информация о сгенерированной группе"),
                    @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Сгенерированная группа не найдена", content = @Content)
            },
            security = @SecurityRequirement(name = "JWT")
    )
    @GetMapping(value = "/generated/{groupId}")
    ResponseEntity<GroupDetailResponse> getGenCoverGroup(
            @AuthenticationPrincipal long userId,
            @PositiveOrZero @PathVariable("groupId") long groupId
    );

    @Operation(
            summary = "Удалить сгенерированную группу пользователя по ID",
            description = "Удаляет сгенерированную группу обложек текущего пользователя с указанным ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Группа успешно удалена"),
                    @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса"),
                    @ApiResponse(responseCode = "404", description = "Сгенерированная группа не найдена")
            },
            security = @SecurityRequirement(name = "JWT")
    )
    @DeleteMapping(value = "/generated/{groupId}")
    ResponseEntity<Void> deleteGenCoverGroup(
            @AuthenticationPrincipal long userId,
            @PositiveOrZero @PathVariable("groupId") long groupId
    );

    @Operation(
            summary = "Получить страницу с сохранёнными группами пользователя",
            description = "Возвращает страницу с сохранёнными группами обложек текущего пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Страница сохранённых групп"),
                    @ApiResponse(responseCode = "204", description = "Сохраненных групп нет", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса", content = @Content)
            },
            security = @SecurityRequirement(name = "JWT")
    )
    @GetMapping(value = "/saved")
    ResponseEntity<PageResponse<GroupItemResponse>> getAllSavedGroups(
            @AuthenticationPrincipal long userId,
            @PositiveOrZero @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @Positive @RequestParam(name = "size", required = false, defaultValue = "10") int size
    );

    @Operation(
            summary = "Получить сохранённую группу пользователя по ID",
            description = "Возвращает подробную информацию о сохранённой группе обложек текущего пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Детальная информация о сохранённой группе"),
                    @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Сохраненная группа не найдена", content = @Content)
            },
            security = @SecurityRequirement(name = "JWT")
    )
    @GetMapping(value = "/saved/{groupId}")
    ResponseEntity<GroupDetailResponse> getSavedGroup(
            @AuthenticationPrincipal long userId,
            @PositiveOrZero @PathVariable("groupId") long groupId
    );

    @Operation(
            summary = "Убрать сохранённую группу пользователя по ID",
            description = "Убирает сохранённую группу обложек из списка сохранённых групп текущего пользователя по её ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Группа успешно удалена"),
                    @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса"),
                    @ApiResponse(responseCode = "404", description = "Сохраненная группа не найдена")
            },
            security = @SecurityRequirement(name = "JWT")
    )
    @DeleteMapping(value = "/saved/{groupId}")
    ResponseEntity<Void> removeSavedGroup(
            @AuthenticationPrincipal long userId,
            @PositiveOrZero @PathVariable("groupId") long groupId
    );
}
