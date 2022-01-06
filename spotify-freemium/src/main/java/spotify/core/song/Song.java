package spotify.core.song;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class Song {
    private final int id;
    private final String name;
    private final int year;
    private final String artist;
    private final String shortName;
    private final int bpm;
    private final int duration;
    private final String genre;
    private final String spotifyId;
    private final String album;

}
