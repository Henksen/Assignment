package spotify.core.artist;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import spotify.core.artist.mapper.FromArtistEntityMapper;
import spotify.core.artist.mapper.ToArtistEntityMapper;
import spotify.core.artist.repo.ArtistEntity;
import spotify.core.artist.repo.ArtistRepository;

@ExtendWith(MockitoExtension.class)
class ArtistServiceTest {

    @Mock
    private ArtistRepository repository;

    @Mock
    private ToArtistEntityMapper toArtistEntityMapper;

    @Mock
    private FromArtistEntityMapper fromArtistEntityMapper;

    @InjectMocks
    private ArtistService sut;

    @Test
    void when_add_artists_expect_only_not_existing_artist_persisted() {
        final Artist artist1 = Artist.builder()
                .name("artist_1")
                .id(1)
                .build();
        final Artist artist2 =  Artist.builder()
                .name("artist_2")
                .id(2)
                .build();

        final List<Artist> artists = List.of(artist1, artist2);

        final ArtistEntity artistEntity1 = new ArtistEntity();
        artistEntity1.setId(1);
        artistEntity1.setName("artist_1");

        final ArtistEntity artistEntity2 = new ArtistEntity();
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

    @Test
    void when_delete_existing_artist_expect_deleted_from_repo() {
        final Integer artistId = 500;
        when(repository.existsById(artistId)).thenReturn(true);

        sut.deleteArtist(artistId);

        verify(repository).deleteById(artistId);
    }

    @Test
    void when_delete_non_existing_artist_expect_not_deleted_from_repo() {
        final Integer artistId = 500;
        when(repository.existsById(artistId)).thenReturn(false);

        sut.deleteArtist(artistId);

        verify(repository, never()).deleteById(artistId);
    }

    @Test
    void when_get_artist_by_id_expect_artist_is_mapped() {
        final Integer artistId = 500;
        final ArtistEntity artistEntity = new ArtistEntity();
        final Artist artist = Artist.builder().build();
        when(repository.findById(artistId)).thenReturn(Optional.of(artistEntity));
        when(fromArtistEntityMapper.map(artistEntity)).thenReturn(artist);

        assertThat(sut.getArtist(artistId)).isEqualTo(artist);
    }

    @Test
    void when_get_artist_by_non_existing_id_expect_exception_is_thrown() {
        final Integer artistId = 500;
        when(repository.findById(artistId)).thenReturn(Optional.empty());

        assertThrows(ArtistNotFoundException.class, () -> sut.getArtist(artistId));
    }
}
