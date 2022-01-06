package spotify.api.song.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeleteSongRequestModel {
    private final Integer id;
}
