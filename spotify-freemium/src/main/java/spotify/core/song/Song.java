package spotify.core.song;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class Song {

    @JsonProperty("Id")
    private final int id;
    @JsonProperty("Name")
    private final String name;
    @JsonProperty("Year")
    private final int year;
    @JsonProperty("Artist")
    private final String artist;
    @JsonProperty("ShortName")
    private final String shortName;
    @JsonProperty("Bpm")
    private final int bpm;
    @JsonProperty("Duration")
    private final int duration;
    @JsonProperty("Genre")
    private final String genre;
    @JsonProperty("SpotifyId")
    private final String spotifyId;
    @JsonProperty("Album")
    private final String album;

}
