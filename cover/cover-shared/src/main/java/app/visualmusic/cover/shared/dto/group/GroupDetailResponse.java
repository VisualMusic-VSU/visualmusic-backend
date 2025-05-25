package app.visualmusic.cover.shared.dto.group;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@Schema(description = "Подробная информация о группе")
public class GroupDetailResponse {
    @Schema(description = "Уникальный идентификатор группы", example = "100")
    private Long id;

    @Schema(description = "Название группы обложек", example = "Новый релиз")
    private String title;

    @Schema(description = "Дата и время создания", example = "2024-05-01T12:34:56Z")
    private Instant createdAt;

    @Schema(description = "Приватность группы обложек", example = "true")
    private boolean isPrivate;

    @Schema(description = "Список обложек группы")
    private Set<CoverItemData> covers;

    @Schema(description = "Список жанров", example = "[\"Рок\", \"Бум-бап\"]")
    private Set<String> genres;

    @Schema(description = "Настроение", example = "Энергичное")
    private String mood;

    @Schema(description = "Стиль изображения обложек", example = "Кандинский")
    private String style;

    @Schema(description = "Текст песни", example = "Это текст песни...")
    private String lyrics;

    @Schema(description = "Промпт для генерации", example = "Рок-группа в стиле киберпанк")
    private String prompt;
}
