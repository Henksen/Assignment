package spotify.api.artist;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import spotify.api.artist.model.CreateArtistsRequestModel;
import spotify.core.repo.ArtistEntity;
import spotify.core.repo.ArtistRepository;
import spotify.core.repo.mapper.ToArtistEntityMapper;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/artists")
public class ArtistRestController {

    private final ArtistRepository artistRepository;

    private final ToArtistEntityMapper artistEntityMapper;

    @PostMapping
    public void addSong(@RequestBody final CreateArtistsRequestModel request) {
        final List<ArtistEntity> artistEntityList = request.getArtists().stream().map(artistEntityMapper::map).collect(Collectors.toList());
        artistEntityList.stream().map(artistRepository::save);
    }

}
