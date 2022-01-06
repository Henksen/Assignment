package spotify.core.song.mapper;

import org.springframework.stereotype.Component;

import spotify.core.common.Mapper;
import spotify.core.song.Song;
import spotify.core.song.repo.SongEntity;

@Component
public class ToSongEntityMapper implements Mapper<Song, SongEntity> {

    @Override
    public SongEntity map(final Song song) {
        final SongEntity songEntity = new SongEntity();
        songEntity.setId(song.getId());
        songEntity.setName(song.getName());
        songEntity.setYear(song.getYear());
        songEntity.setArtist(song.getArtist());
        songEntity.setShortName(song.getShortName());
        songEntity.setBpm(song.getBpm());
        songEntity.setDuration(song.getDuration());
        songEntity.setGenre(song.getGenre());
        songEntity.setSpotifyId(song.getSpotifyId());
        songEntity.setAlbum(song.getAlbum());
        return songEntity;
    }
}
