package spotify;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import spotify.api.artist.model.CreateArtistsRequestModel;
import spotify.api.artist.model.DeleteArtistsRequestModel;
import spotify.core.artist.Artist;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void when_create_artists_expect_200() throws Exception{
        final CreateArtistsRequestModel createArtistsRequestModel = CreateArtistsRequestModel.builder()
                .artists(List.of(createArtist(999997)))
                .build();

        sendPostRequest(createArtistsRequestModel, "/api/artists")
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void when_delete_artist_expect_200() throws Exception{
        final DeleteArtistsRequestModel deleteArtistsRequestModel = DeleteArtistsRequestModel.builder()
                .id(999997)
                .build();

        sendDeleteRequest(deleteArtistsRequestModel, "/api/artists")
                .andExpect(status().isOk())
                .andReturn();
    }

    private Artist createArtist(final int id) {
        return Artist.builder().id(id).name("testName" + id).build();
    }

    private ResultActions sendDeleteRequest(final DeleteArtistsRequestModel request, final String url) throws Exception {
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
