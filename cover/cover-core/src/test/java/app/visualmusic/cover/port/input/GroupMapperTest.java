package app.visualmusic.cover.port.input.mapper;

import app.visualmusic.cover.domain.Cover;
import app.visualmusic.cover.domain.Group;
import app.visualmusic.cover.shared.dto.group.GroupItemResponse;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GroupMapperTest {

    private final GroupMapper mapper = GroupMapper.INSTANCE;

    @Test
    void toItem_shouldMapGroupWithCovers() {

        Group group = mock(Group.class);
        when(group.getId()).thenReturn(1L);
        when(group.getTitle()).thenReturn("Test Group");


        Cover cover = mock(Cover.class);
        Set<Cover> covers = Collections.singleton(cover);
        when(group.getCovers()).thenReturn(covers);


        Function<Cover, String> coverImageUrlProvider = c -> "http://example.com/image.jpg";


        GroupItemResponse result = mapper.toItem(group, coverImageUrlProvider);


        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Group", result.getTitle());
        assertNotNull(result.getCovers());
        assertFalse(result.getCovers().isEmpty());


        assertEquals("http://example.com/image.jpg", result.getCovers().get(0).getImageUrl());
    }
}
