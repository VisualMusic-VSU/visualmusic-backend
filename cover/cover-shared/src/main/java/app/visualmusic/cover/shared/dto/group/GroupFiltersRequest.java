package app.visualmusic.cover.shared.dto.group;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Фильтры для фильтрации групп обложек")
public class GroupFiltersRequest {

    @Schema(description = "Список идентификаторов жанров", example = "[1, 2, 3]")
    private List<Long> genreIds = new ArrayList<>();

    @Schema(description = "Идентификатор настроения", example = "5")
    private Long moodId;

    @Schema(description = "Идентификатор стиля изображения", example = "8")
    private Long styleId;
}
