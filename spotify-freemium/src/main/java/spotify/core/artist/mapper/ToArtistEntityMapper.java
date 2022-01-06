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
        artistEntity.setId(artist.getId());
        artistEntity.setName(artist.getName());
        return artistEntity;
    }
}
