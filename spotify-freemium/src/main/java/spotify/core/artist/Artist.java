package spotify.core.artist;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Artist {

    private final Integer id;

    private final String name;

}
