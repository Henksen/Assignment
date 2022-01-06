package spotify.api.song.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import spotify.core.song.Song;

@Getter
@Builder
public class CreateSongRequestModel {
    private final List<Song> songs;
}
