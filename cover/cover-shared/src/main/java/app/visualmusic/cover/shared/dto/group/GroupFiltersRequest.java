package app.visualmusic.cover.shared.dto.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupFiltersRequest {
    private List<Long> genreIds = new ArrayList<>();

    private Long moodId;

    private Long styleId;
}
