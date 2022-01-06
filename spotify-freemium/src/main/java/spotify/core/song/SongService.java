package spotify.core.song;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import spotify.core.song.mapper.ToSongEntityMapper;
import spotify.core.song.repo.SongRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class SongService {

    private final SongRepository songRepository;

    private final ToSongEntityMapper toSongEntityMapper;

    public void addSongs(final List<Song> songs) {
        songs.forEach(song -> {
            if(!songRepository.existsById(song.getId())) {
                log.info("Saving song {} with id {}", song.getName(), song.getId());
                songRepository.save(toSongEntityMapper.map(song));
            }
        });
    }

    public void deleteSong(final Integer id) {
        if(songRepository.existsById(id)) {
            log.info("Deleting with id {}", id);
            songRepository.deleteById(id);
        }
    }

}
