package spotify.api.artist;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import spotify.api.artist.model.DeleteArtistsRequestModel;
import spotify.core.artist.Artist;
import spotify.core.artist.ArtistService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/artists")
public class ArtistRestController {

    private final ArtistService artistService;

    @PostMapping
    public void addArtist(@RequestBody final List<Artist> request) {
        artistService.addArtists(request);
    }

    @DeleteMapping
    public void deleteArtist(@RequestBody final DeleteArtistsRequestModel request) {
        artistService.deleteArtist(request.getId());
    }

    @GetMapping("/{artistId}")
    public Artist getArtist(@PathVariable final Integer artistId) {
        return artistService.getArtist(artistId);
    }
}
