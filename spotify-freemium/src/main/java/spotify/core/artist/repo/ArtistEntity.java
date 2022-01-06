package spotify.core.artist.repo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.Setter;

@Entity(name="artist")
@Getter
@Setter
public class ArtistEntity {

    @Id
    @Column(nullable=false)
    private int id;

    @Nullable
    private String name;
}
