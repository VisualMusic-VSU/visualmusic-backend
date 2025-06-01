package app.visualmusic.cover.shared.dto.group;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Краткая информация о группе обложек")
public class GroupItemResponse {
    @Schema(description = "Уникальный идентификатор группы", example = "10")
    private Long id;

    @Schema(description = "Название группы", example = "Новый релиз")
    private String title;

    @Schema(description = "Список обложек группы")
    private List<CoverItemData> covers;
}
