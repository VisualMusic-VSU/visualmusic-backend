package app.visualmusic.cover.shared.dto.group;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Информация об обложке")
public class CoverItemData {
    @Schema(description = "Уникальный идентификатор", example = "42")
    private Long id;

    @Schema(description = "URL изображения обложки в MinIO", example = "bucket/image.jpg")
    private String imageUrl;
}
