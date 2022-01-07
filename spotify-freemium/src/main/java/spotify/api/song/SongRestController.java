package spotify.api.song;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import spotify.api.song.model.DeleteSongRequestModel;
import spotify.core.song.Song;
import spotify.core.song.SongService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/songs")
public class SongRestController {

    private final SongService songService;

    @PostMapping
    public void addSongs(@RequestBody final List<Song> request) {
        songService.addSongs(request);
    }

    @DeleteMapping
    public void deleteSong(@RequestBody final DeleteSongRequestModel request) {
        songService.deleteSong(request.getId());
    }

}
