package spotify.core.artist;

public class ArtistNotFoundException extends RuntimeException{
    public ArtistNotFoundException(final String message) {
        super(message);
    }
}
