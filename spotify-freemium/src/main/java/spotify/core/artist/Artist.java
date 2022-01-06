package spotify.core.artist;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Artist {

    @JsonProperty("Id")
    private final Integer id;
    @JsonProperty("Name")
    private final String name;

}
