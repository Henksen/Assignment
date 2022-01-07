package spotify.api.configuration;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;
import spotify.core.artist.ArtistNotFoundException;

@Slf4j
@ControllerAdvice
public class RestControllerExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ArtistNotFoundException.class)
    public void artistNotFoundException(final ArtistNotFoundException e) {
        logWarn(e);
    }

    private static void logWarn(final Throwable throwable) {
        log.warn(String.format("A %s occurred", throwable.getClass().getSimpleName()), throwable);
    }
}
