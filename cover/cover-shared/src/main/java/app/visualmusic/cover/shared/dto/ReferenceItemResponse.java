package app.visualmusic.cover.shared.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Справочные данные: жанр, стиль или настроение")
public class ReferenceItemResponse {
    @Schema(description = "Уникальный идентификатор", example = "1")
    private Long id;

    @Schema(description = "Название элемента", example = "Грустное")
    private String name;
}
