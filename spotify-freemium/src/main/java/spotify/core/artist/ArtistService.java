package spotify.core.artist;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;
import spotify.core.artist.repo.ArtistEntity;
import spotify.core.artist.repo.ArtistRepository;
import spotify.core.artist.repo.mapper.ToArtistEntityMapper;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    private final ToArtistEntityMapper artistEntityMapper;

    @PostMapping
    public void addArtists(@RequestBody final List<Artist> artists) {
        final List<ArtistEntity> artistEntities = artists
                .stream()
                .map(artistEntityMapper::map)
                .collect(Collectors.toList());

        artistRepository.saveAll(artistEntities);
    }

}
