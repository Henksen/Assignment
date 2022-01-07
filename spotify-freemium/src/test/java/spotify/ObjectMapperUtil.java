package spotify;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.springframework.test.web.servlet.MvcResult;

public final class ObjectMapperUtil {
    private ObjectMapperUtil() {
    }

    public static <T> T fromJson(MvcResult mvcResult, ObjectMapper objectMapper, Class<T> clazz) {
        byte[] contentAsByteArray = mvcResult.getResponse().getContentAsByteArray();

        try {
            return objectMapper.readValue(contentAsByteArray, clazz);
        } catch (IOException var5) {
            throw new IllegalArgumentException(String.format("Could not read %s", clazz.getSimpleName()), var5);
        }
    }

    public static String toJson(ObjectMapper objectMapper, Object content) {
        try {
            return objectMapper.writeValueAsString(content);
        } catch (JsonProcessingException var3) {
            throw new IllegalArgumentException(var3);
        }
    }
}
