package spotify.core.artist;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import spotify.core.artist.mapper.ToArtistEntityMapper;
import spotify.core.artist.repo.ArtistRepository;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    private final ToArtistEntityMapper artistEntityMapper;

    public void addArtists(final List<Artist> artists) {
        artists
                .forEach(artist -> {
                    if(artistRepository.existsById(artist.getId())) {
                        artistRepository.save(artistEntityMapper.map(artist));
                    }
                });
    }

    public void deleteArtist(final Integer id) {
        if(artistRepository.existsById(id)) {
            artistRepository.deleteById(id);
        }
    }
}
