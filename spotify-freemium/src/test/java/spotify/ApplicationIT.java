package spotify;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static spotify.ObjectMapperUtil.fromJson;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import spotify.api.artist.model.DeleteArtistsRequestModel;
import spotify.api.song.model.DeleteSongRequestModel;
import spotify.core.artist.Artist;
import spotify.core.song.Song;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final int ARTIST_ID = 88999999;
    private final int SONG_ID = 88999999;

    @Test
    void when_create_artists_expect_200() throws Exception{
        final List<Artist> request = List.of(createArtist(ARTIST_ID));

        sendPostRequest(request, "/api/artists")
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void when_get_artist_expect_artist() throws Exception{
        final MvcResult result = sendGetRequest("/api/artists/" + ARTIST_ID)
                .andExpect(status().isOk())
                .andReturn();

        final Artist artist = fromJson(result, objectMapper, Artist.class);
        assertThat(artist.getId()).isEqualTo(ARTIST_ID);
        assertThat(artist.getName()).isEqualTo("testName" + ARTIST_ID);
    }

    @Test
    void when_delete_artist_expect_200() throws Exception{
        final DeleteArtistsRequestModel request = DeleteArtistsRequestModel.builder()
                .id(ARTIST_ID)
                .build();

        sendDeleteRequest(request, "/api/artists")
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void when_create_song_expect_200() throws Exception{
        final List<Song> request = List.of(buildSong());

        sendPostRequest(request, "/api/songs")
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void when_delete_song_expect_200() throws Exception{
        final DeleteSongRequestModel request = DeleteSongRequestModel.builder()
                .id(SONG_ID)
                .build();

        sendDeleteRequest(request, "/api/songs")
                .andExpect(status().isOk())
                .andReturn();
    }

    private Song buildSong() {
        return Song.builder()
                .id(SONG_ID)
                .name("twinkle twinkle")
                .year(1999)
                .album("granny")
                .shortName("tw")
                .bpm(200)
                .duration(30)
                .genre("child songs")
                .album("starry night")
                .build();
    }

    private Artist createArtist(final int id) {
        return Artist.builder().id(id).name("testName" + id).build();
    }

    private ResultActions sendGetRequest(final String url) throws Exception {
        return mvc.perform(get(url).headers(new HttpHeaders()));
    }

    private ResultActions sendDeleteRequest(final Object request, final String url) throws Exception {
        return sendRequest(delete(url), toJson(objectMapper, request), new HttpHeaders());
    }

    private ResultActions sendPostRequest(final Object request, final String url) throws Exception {
        return sendRequest(post(url), toJson(objectMapper, request), new HttpHeaders());
    }
    private ResultActions sendRequest(final MockHttpServletRequestBuilder requestBuilder, final String json, final HttpHeaders httpHeaders) throws Exception {
        return mvc.perform(requestBuilder.contentType(APPLICATION_JSON)
                .headers(httpHeaders)
                .content(json));
    }

    public static String toJson(ObjectMapper objectMapper, Object content) {
        try {
            return objectMapper.writeValueAsString(content);
        } catch (JsonProcessingException var3) {
            throw new IllegalArgumentException(var3);
        }
    }
}
