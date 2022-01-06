package spotify.core.artist;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import spotify.core.artist.repo.ArtistRepository;
import spotify.core.artist.mapper.ToArtistEntityMapper;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArtistService {

    private final ArtistRepository artistRepository;

    private final ToArtistEntityMapper toArtistEntityMapper;

    public void addArtists(final List<Artist> artists) {
        artists.forEach(artist -> {
                    if(!artistRepository.existsById(artist.getId())) {
                        log.info("Saving artist {} with id {}", artist.getName(), artist.getId());
                        artistRepository.save(toArtistEntityMapper.map(artist));
                    }
                });
    }

    public void deleteArtist(final Integer id) {
        if(artistRepository.existsById(id)) {
            log.info("Deleting with id {}", id);
            artistRepository.deleteById(id);
        }
    }
}
