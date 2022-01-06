package spotify.api.artist.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import spotify.core.artist.Artist;

@Getter
@Builder
public class CreateArtistsRequestModel {
    private final List<Artist> artists;
}
