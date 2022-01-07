package spotify.core.artist.mapper;

import org.springframework.stereotype.Component;

import spotify.core.artist.Artist;
import spotify.core.artist.repo.ArtistEntity;
import spotify.core.common.Mapper;

@Component
public class FromArtistEntityMapper implements Mapper<ArtistEntity, Artist> {

    @Override
    public Artist map(final ArtistEntity artistEntity) {
        return Artist.builder()
                .id(artistEntity.getId())
                .name(artistEntity.getName())
                .build();
    }
}
