package moe.pine.stkrep.ua.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.List;

@UtilityClass
public class Parser {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public List<UserAgent> parse(InputStream stream) {
        try {
            return OBJECT_MAPPER.readValue(stream, new TypeReference<List<UserAgent>>() {
            });
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
