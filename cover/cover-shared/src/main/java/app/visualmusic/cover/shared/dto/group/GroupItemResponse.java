package app.visualmusic.cover.shared.dto.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupItemResponse {
    private Long id;

    private String title;

    private List<CoverItemData> covers;
}
