package spotify.api.artist.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeleteArtistsRequestModel {
    private final Integer id;
}
