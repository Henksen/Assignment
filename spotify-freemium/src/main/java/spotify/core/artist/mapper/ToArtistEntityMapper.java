package spotify.core.artist.mapper;

import org.springframework.stereotype.Component;

import spotify.core.artist.Artist;
import spotify.core.common.Mapper;
import spotify.core.artist.repo.ArtistEntity;

@Component
public class ToArtistEntityMapper implements Mapper<Artist, ArtistEntity> {

    @Override
    public ArtistEntity map(final Artist artist) {
        final ArtistEntity artistEntity = new ArtistEntity();
        artistEntity.setId(artistEntity.getId());
        artistEntity.setName(artistEntity.getName());
        return artistEntity;
    }
}
