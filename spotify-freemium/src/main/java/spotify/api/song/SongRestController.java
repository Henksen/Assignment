package spotify.api.song;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import spotify.api.song.model.CreateSongRequestModel;
import spotify.api.song.model.DeleteSongRequestModel;
import spotify.core.song.SongService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/songs")
public class SongRestController {

    private final SongService songService;

    @PostMapping
    public void addSongs(@RequestBody final CreateSongRequestModel request) {
        songService.addSongs(request.getSongs());
    }

    @DeleteMapping
    public void deleteArtist(@RequestBody final DeleteSongRequestModel request) {
        songService.deleteSong(request.getId());
    }
}
