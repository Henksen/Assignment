package spotify.core.artist;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import spotify.core.artist.mapper.ToArtistEntityMapper;
import spotify.core.artist.repo.ArtistEntity;
import spotify.core.artist.repo.ArtistRepository;

@ExtendWith(MockitoExtension.class)
class ArtistServiceTest {

    @Mock
    private ArtistRepository repository;

    @Mock
    private ToArtistEntityMapper toArtistEntityMapper;

    @InjectMocks
    private ArtistService sut;

    @Test
    void when_add_artists_only_not_existing_artist_persisted() {
        final Artist artist1 = Artist.builder()
                .name("artist_1")
                .id(1)
                .build();
        final Artist artist2 =  Artist.builder()
                .name("artist_2")
                .id(2)
                .build();

        final List<Artist> artists = List.of(artist1, artist2);

        ArtistEntity artistEntity1 = new ArtistEntity();
        artistEntity1.setId(1);
        artistEntity1.setName("artist_1");

        ArtistEntity artistEntity2 = new ArtistEntity();
        artistEntity1.setId(2);
        artistEntity1.setName("artist_2");

        lenient().when(toArtistEntityMapper.map(artist1)).thenReturn(artistEntity1);
        lenient().when(toArtistEntityMapper.map(artist2)).thenReturn(artistEntity2);

        when(repository.existsById(artist1.getId())).thenReturn(false);
        when(repository.existsById(artist2.getId())).thenReturn(true);

        sut.addArtists(artists);

        verify(repository).save(artistEntity1);
        verify(repository, never()).save(artistEntity2);
    }
}
