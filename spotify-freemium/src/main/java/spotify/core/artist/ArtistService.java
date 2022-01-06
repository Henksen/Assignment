package spotify.core.artist;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import spotify.core.artist.repo.ArtistEntity;
import spotify.core.artist.repo.ArtistRepository;
import spotify.core.artist.mapper.ToArtistEntityMapper;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    private final ToArtistEntityMapper artistEntityMapper;

    public void addArtists(final List<Artist> artists) {
        final List<ArtistEntity> artistEntities = artists
                .stream()
                .map(artistEntityMapper::map)
                .collect(Collectors.toList());
        artistRepository.saveAll(artistEntities);
    }

    public void deleteArtist(final Integer id) {
        if(artistRepository.existsById(id)) {
            artistRepository.deleteById(id);
        }
    }
}
