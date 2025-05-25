package app.visualmusic.cover.shared.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Страница с данными")
public class PageResponse<T> {
    @Schema(description = "Список элементов на текущей странице")
    private List<T> content;

    @Schema(description = "Номер текущей страницы", example = "0")
    private int page;

    @Schema(description = "Есть ли следующая страница?", example = "true")
    private boolean hasNext;
}
