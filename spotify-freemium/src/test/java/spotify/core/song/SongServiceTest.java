package spotify.core.song;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import spotify.core.song.mapper.ToSongEntityMapper;
import spotify.core.song.repo.SongEntity;
import spotify.core.song.repo.SongRepository;

@ExtendWith(MockitoExtension.class)
class SongServiceTest {
    @Mock
    private SongRepository repository;

    @Mock
    private ToSongEntityMapper toSongEntityMapper;

    @InjectMocks
    private SongService sut;

    @Test
    void when_add_songs_expect_only_not_existing_song_persisted() {
        final Song song1 = Song.builder()
                .name("song_1")
                .id(1)
                .build();
        final Song song2 =  Song.builder()
                .name("song_2")
                .id(2)
                .build();

        final List<Song> songs = List.of(song1, song2);

        final SongEntity songEntity1 = new SongEntity();
        songEntity1.setId(1);
        songEntity1.setName("song_1");

        final SongEntity songEntity2 = new SongEntity();
        songEntity2.setId(2);
        songEntity2.setName("song_2");

        lenient().when(toSongEntityMapper.map(song1)).thenReturn(songEntity1);
        lenient().when(toSongEntityMapper.map(song2)).thenReturn(songEntity2);

        when(repository.existsById(song1.getId())).thenReturn(false);
        when(repository.existsById(song2.getId())).thenReturn(true);

        sut.addSongs(songs);

        verify(repository).save(songEntity1);
        verify(repository, never()).save(songEntity2);
    }

    @Test
    void when_delete_existing_artist_expect_deleted_from_repo() {
        final Integer songId = 500;
        when(repository.existsById(songId)).thenReturn(true);

        sut.deleteSong(songId);

        verify(repository).deleteById(songId);
    }

    @Test
    void when_delete_non_existing_artist_expect_not_deleted_from_repo() {
        final Integer songId = 500;
        when(repository.existsById(songId)).thenReturn(false);

        sut.deleteSong(songId);

        verify(repository, never()).deleteById(songId);
    }
}
