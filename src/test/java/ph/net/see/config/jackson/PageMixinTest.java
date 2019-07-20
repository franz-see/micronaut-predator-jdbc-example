package ph.net.see.config.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import io.micronaut.data.model.Page;
import org.junit.jupiter.api.Test;
import ph.net.see.model.Genre;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PageMixinTest extends AbstractMixinTest {

    @Test
    void shouldBeDeserializable() throws IOException {
        String json = "{\"content\":[{\"id\":1,\"name\":\"DevOps\"},{\"id\":2,\"name\":\"Micro-services\"}],\"pageable\":{\"number\":0,\"sort\":{\"sorted\":false},\"size\":100,\"offset\":0,\"sorted\":false},\"totalSize\":2,\"totalPages\":1,\"empty\":false,\"size\":100,\"offset\":0,\"pageNumber\":0,\"numberOfElements\":2,\"sort\":{\"number\":0,\"sort\":{\"sorted\":false},\"size\":100,\"offset\":0,\"sorted\":false}}";
        Page page = objectMapper.readValue(json, new TypeReference<Page<Genre>>() {});
        assertNotNull(page);

        assertEquals(Genre.class, page.getContent().get(0).getClass());
    }

}