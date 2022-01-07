package spotify.api.artist;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import spotify.api.artist.model.CreateArtistsRequestModel;
import spotify.api.artist.model.DeleteArtistsRequestModel;
import spotify.core.artist.ArtistService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/artists")
public class ArtistRestController {

    private final ArtistService artistService;

    @PostMapping
    public void addArtist(@RequestBody final CreateArtistsRequestModel request) {
        artistService.addArtists(request.getArtists());
    }

    @DeleteMapping
    public void deleteArtist(@RequestBody final DeleteArtistsRequestModel request) {
        artistService.deleteArtist(request.getId());
    }

    @GetMappping("/{artistId}")
    public Artist getArtist(@PathVariable final Integer artistId) {
        artistService.getArtist(request.getId());
    }
}
