package spotify.core.song.repo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.Setter;

@Entity(name="song")
@Getter
@Setter
public class SongEntity {
    @Id
    @Column(nullable=false)
    private int id;
    @Nullable
    private String name;
    @Nullable
    private int year;
    @Nullable
    private String artist;
    private String shortName;
    private int bpm;
    private int duration;
    @Nullable
    private String genre;
    private String spotifyId;
    private String album;
}
