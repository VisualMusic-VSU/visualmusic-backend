package app.visualmusic.auth.port.input.spring.web;

import app.visualmusic.auth.shared.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Аутентификация", description = "Операции для регистрации, авторизации и JWT токенов")
@RequestMapping(
        value = "/api/v1/auth",
        produces = "application/json"
)
public interface AuthRestApi {

    @Operation(
            summary = "Регистрация нового пользователя",
            description = "Создаёт нового пользователя с указанными данными",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешная регистрация"),
                    @ApiResponse(responseCode = "400", description = "Неверные данные запроса")
            }
    )
    @PostMapping(value = "/register")
    ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest request);

    @Operation(
            summary = "Вход пользователя",
            description = "Аутентифицирует пользователя и возвращает пару JWT токенов",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный вход",
                            content = @Content(schema = @Schema(implementation = TokensResponse.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Неверные данные запроса", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Неверный пароль", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден", content = @Content)
            }
    )
    @PostMapping(value = "/login")
    ResponseEntity<TokensResponse> login(@Valid @RequestBody LoginRequest request);

    @Operation(
            summary = "Получение нового JWT токена доступа",
            description = "Получение нового JWT токена доступа токена, используя JWT токен обновления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Новый JWT токен доступа",
                            content = @Content(schema = @Schema(implementation = AccessTokenResponse.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Неверные данные запроса", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Недействительный JWT токен обновления", content = @Content)
            }
    )
    @PostMapping(value = "/token")
    ResponseEntity<AccessTokenResponse> getNewAccessToken(@Valid @RequestBody GetNewAccessTokenRequest request);

    @Operation(
            summary = "Выход пользователя",
            description = "Аннулирует JWT токен доступа для указанного устройства",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный выход"),
                    @ApiResponse(responseCode = "400", description = "Неверные данные запроса")
            }
    )
    @PostMapping(value = "/logout")
    ResponseEntity<Void> logout(@Valid @RequestBody LogoutRequest request);
}
